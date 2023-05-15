package top.mnsx.spring_mnsx.container;

import lombok.extern.slf4j.Slf4j;
import top.mnsx.spring_mnsx.annotation.aop.Around;
import top.mnsx.spring_mnsx.annotation.aop.Aspect;
import top.mnsx.spring_mnsx.annotation.component.Component;
import top.mnsx.spring_mnsx.annotation.component.Controller;
import top.mnsx.spring_mnsx.annotation.component.Repository;
import top.mnsx.spring_mnsx.annotation.component.Service;
import top.mnsx.spring_mnsx.annotation.injection.Autowired;
import top.mnsx.spring_mnsx.annotation.injection.Qualifier;
import top.mnsx.spring_mnsx.annotation.transaction.Transactional;
import top.mnsx.spring_mnsx.aop.CGLIBProxy;
import top.mnsx.spring_mnsx.container.exception.*;
import top.mnsx.spring_mnsx.container.generator.AnnotationGenerator;
import top.mnsx.spring_mnsx.container.parser.XmlSpringConfigParser;
import top.mnsx.spring_mnsx.jdbc.TransactionProxy;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 14:22
 * @Description: IOC容器启动类
 */
@Slf4j
public class ClassPathXmlApplication {
    // 单例设计模式，禁止通过new创建上下文
    private static final ClassPathXmlApplication CONTEXT = new ClassPathXmlApplication();
    // Spring配置类
    private String springConfig;
    // 存放class文件对应类指定包路径集合
    private final List<String> classPaths = new ArrayList<>();
    // 存放Controller的哈希表，类作为键，对象作为值
    private final Map<Class<?>, Object> controllerMap = new HashMap<>();
    // IOC容器：接口容器、类容器、名称容器
    private final Map<Class<?>, List<Object>> iocInterfacesContainer = new ConcurrentHashMap<>();
    private final Map<String, Object> iocNameContainer = new ConcurrentHashMap<>();
    private final Map<Class<?>, List<Object>> iocClassesContainer = new ConcurrentHashMap<>();
    // 存放被@Aspect修饰的类的集合
    private final Set<Class<?>> aopClasses = new CopyOnWriteArraySet<>();
    // 存放被代理的类的集合
    private final Set<Class<?>> proxyClassSet = new CopyOnWriteArraySet<>();
    // 存放Transaction方法的集合
    private final List<String> transactionalMethods = new CopyOnWriteArrayList<>();
    private final Set<Class<?>> transactionalClassSet = new CopyOnWriteArraySet<>();

    /**
     * 通过静态方法获取上下文
     * @return 单例设计模式返回上下文
     */
    public static ClassPathXmlApplication getContext() {
        return CONTEXT;
    }

    /**
     * 通过名称获取Bean
     * @param beanType Bean名称
     * @return 返回Bean
     */
    public Object getBean(Class<?> beanType) {
        return this.getBean(null, beanType);
    }

    /**
     * 通过名称和类型获取Bean
     * @param beanName Bean名称
     * @param beanType Bean类型
     * @return 返回Bean
     */
    public Object getBean(String beanName, Class<?> beanType) {
        List<Object> beans = iocClassesContainer.get(beanType);
        if (beans.size() == 0) {
            throw new BeanNotMatchException("没有Bean符合要求被匹配");
        } else if (beans.size() > 1) {
            throw new ToMuchBeanExistException("有多个Bean符合要求，这样是被禁止的");
        }
        if (beanName != null) {
            Object bean = iocNameContainer.get(beanName);
            if (bean != beans.get(0)) {
                throw new BeanNameTypeNotConformException("Bean的名称和类型不匹配");
            }
            return bean;
        }
        return beans.get(0);
    }

    /**
     * 通过类型获取Bean
     * @param beanName Bean类型
     * @return 返回Bean
     */
    public Object getBean(String beanName) {
        Object bean = iocNameContainer.get(beanName);
        if (bean == null) {
            throw new BeanNotMatchException("没有Bean符合要求被匹配");
        }
        return bean;
    }

    /**
     * 将构造函数设置为私有，禁止通过new进行创建
     */
    private ClassPathXmlApplication() {

    }

    public Map<Class<?>, Object> getControllerMap() {
        return controllerMap;
    }

    /**
     * 引导类
     * @param springConfig 配置文件位置
     */
    public void bootStrap(String springConfig) {
        log.info("引导程序启动");
        this.springConfig = springConfig;

        // 展示title
        this.showTitle();

        // 核心方法
        refresh();
    }

    /**
     * 展示title
     */
    private void showTitle() {
        InputStream bannerInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("banner.txt");
        if (bannerInputStream == null) {
            return;
        }

        int n = -1;
        byte[] bytes = new byte[2048];

        try {
            while ((n = bannerInputStream.read(bytes, 0, bytes.length)) != -1) {
                String str = new String(bytes, 0, n);
                System.out.println(str);
            }
            bannerInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IOC容器初始化核心方法
     */
    private void refresh() {
        String basePackage = XmlSpringConfigParser.getBasePackage(springConfig);
        log.info("XML文件解析结束，获取默认包扫描路径{}", basePackage);
        // 扫描指定包路径
        this.loadClasses(basePackage);
        // 扫描项目中自带的Bean添加到容器
        this.loadClasses("top.mnsx.spring_mnsx");
        log.info("已完成包路径路径，并存放到classPaths中");
        // 执行容器初始化
        this.doInitInstance();
        log.info("结束IOC容器初始化，根据class、name、interface生成IOC容器");
        // 事务管理
        this.doTransaction();
        log.info("完成事务管理添加");
        // 实现AOP，创建代理对象
        this.doAop();
        log.info("已经生成代理对象，并且替换原始对象");
        // 进行依赖注入
        this.doInjection();
        log.info("完成依赖注入");
    }

    /**
     * 将扫描指定包路径将其中classes包路径存入list
     * @param basePackage 扫描包路径
     */
    private void loadClasses(String basePackage) {
        // 获取classpath绝对路径
        URL classpathUrl = Thread.currentThread().getContextClassLoader().getResource("");
        // 将包扫描路径改为文件路径
        basePackage = basePackage.replace(".", File.separator);

        // 解析指定路径获取文件类
        File file = null;
        if (classpathUrl != null) {
            file = new File(classpathUrl.toString().replace("file:/", "") + basePackage);
        } else {
            throw new URLNotFoundException("无法找到指定路径");
        }
        
        // 指定路径中的测试路径test-classes转换为classes，在包文件中没有test-classes，需要将其转换为classes
        String packagePath = file.toString();
        if (packagePath.contains("test-classes")) {
            packagePath = packagePath.replace("test-classes", "classes");
        }

        // 解析指定扫描路径下所有的class文件
        this.findAllClasses(new File(packagePath));
    }

    /**
     * 遍历指定文件，递归解析所有的文件路径，将其转换为包路径，存放如List中
     * @param paramFile 文件参数
     */
    private void findAllClasses(File paramFile) {
        // 获取该文件下的所有子文件
        File[] files = paramFile.listFiles();
        if (files != null) {
            for (File file : files) {
                // 将所有class文件转换为包路径
                if (!file.isDirectory()) {
                    String filePath = file.getPath();
                    if (filePath.endsWith("class")) {
                        filePath = this.handlerPath(filePath);
                        classPaths.add(filePath);
                    }
                } else {
                    // 如果时文件，那么递归执行
                    this.findAllClasses(file);
                }
            }
        }
    }

    /**
     * 将class文件路径转换为包路径
     * @param path class文件路径
     * @return 返回包路径
     */
    private String handlerPath(String path) {
        int index = path.indexOf("classes" + File.separator);
        // 去除开头的classes+File.separator，去除结尾的.class
        path = path.substring(index + 8, path.length() - 6);
        // 将文件路径转换为包路径
        path = path.replace(File.separator, ".");
        return path;
    }

    /**
     * 将classPaths中所有路径的类初始化并添加到IOC容器中
     */
    private void doInitInstance() {
        // 解析集合中所有路径
        for (String classPath : classPaths) {
            try {
                // 通过类路径构建类
                Class<?> componentClass = Class.forName(classPath);

                // 判断该类是否被@Aspect标注，如果需要被动态代理，那么将其加入集合中
                if (componentClass.isAnnotationPresent(Aspect.class)) {
                    aopClasses.add(componentClass);
                    continue;
                }

                // 获取组件注解集合
                List<Class<? extends Annotation>> annotationList = AnnotationGenerator.getAnnotationList();

                for (Class<? extends Annotation> annotation : annotationList) {
                    // 如果类被指定注解标记，那么生成该类的对象并且将其放入容器中
                    if (componentClass.isAnnotationPresent(annotation)) {
                        Object componentObj = componentClass.newInstance();
                        // 获取所有的Controller并且放入集合中，为后期HandlerMapper准备
                        if (componentClass.isAnnotationPresent(Controller.class)) {
                            controllerMap.put(componentClass, componentObj);
                        }

                        // 判断该类是否被@Transactional注解修饰
                        boolean isClass = componentClass.isAnnotationPresent(Transactional.class);
                        Method[] declaredMethods = componentClass.getDeclaredMethods();
                        if (declaredMethods.length != 0) {
                            for (Method declaredMethod : declaredMethods) {
                                // 判断方法是否被@Transactional注解修饰
                                boolean isMethod = declaredMethod.isAnnotationPresent(Transactional.class);
                                if (isClass || isMethod) {
                                    transactionalMethods.add(declaredMethod.getName());
                                    transactionalClassSet.add(componentClass);
                                }
                            }
                        }

                        Class<?>[] interfaces = componentClass.getInterfaces();
                        for (Class<?> componentInterface : interfaces) {
                            // 验证IOC容器中是否存在该组件
                            List<Object> componentList = iocInterfacesContainer.get(componentInterface);
                            // 如果不存在，新建一个集合，将其加入IOC容器
                            if (componentList == null) {
                                componentList = new ArrayList<>();
                                componentList.add(componentObj);
                                // 将接口作为键， 将继承该接口的对象的集合加入IOC
                                iocInterfacesContainer.put(componentInterface, componentList);
                            } else {
                                componentList.add(componentObj);
                            }
                        }

                        // 将类作为键，将类作为键，将对象作为值加入IOC
                        if (iocClassesContainer.containsKey(componentClass)) {
                            iocClassesContainer.get(componentClass).add(componentObj);
                        } else {
                            List<Object> list = new ArrayList<>();
                            list.add(componentObj);
                            iocClassesContainer.put(componentClass, list);
                        }

                        // 获取组件类上的注解（待优化）
                        Controller controllerAnnotation = componentClass.getAnnotation(Controller.class);
                        Service serviceAnnotation = componentClass.getAnnotation(Service.class);
                        Repository repositoryAnnotation = componentClass.getAnnotation(Repository.class);
                        Component componentAnnotation = componentClass.getAnnotation(Component.class);

                        // 获取注解的属性value
                        String value = "";
                        if (controllerAnnotation != null) {
                            value = controllerAnnotation.value();
                        } else if (serviceAnnotation != null) {
                            value = serviceAnnotation.value();
                        } else if (repositoryAnnotation != null) {
                            value = repositoryAnnotation.value();
                        } else if (componentAnnotation != null) {
                            value = componentAnnotation.value();
                        }

                        // 是否声明value，没有value则使用类名作为名称
                        String objName = "";
                        if ("".equals(value)) {
                            String simpleName = componentClass.getSimpleName();
                            objName = (simpleName.charAt(0) + "").toLowerCase() + simpleName.substring(1);
                        } else {
                            objName = value;
                        }

                        // 判断IOC容器中是否存在该组件
                        if (iocNameContainer.containsKey(objName)) {
                            throw new BeanHasExistException("Bean已经存在，名称为" + objName);
                        } else {
                            // 如果容器中不存在那么新建
                            iocNameContainer.put(objName, componentObj);
                        }
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事务管理，添加
     */
    private void doTransaction() {
        // 将需要进行事务处理的方法添加AOP
        for (Class<?> componentClass : transactionalClassSet) {
            Object proxyObj = null;
            if (transactionalMethods.size() > 0) {
                doInjectionOneClass(componentClass);
                TransactionProxy transactionProxy = new TransactionProxy(componentClass, transactionalMethods);
                // 将代理类赋值给对象
                proxyObj = transactionProxy.getProxyInstance();
            }
            updateIOCContainer(proxyObj, componentClass);
        }
    }

    /**
     * AOP动态代理，被声明为被代理的类替换为代理类
     */
    private void doAop() {
        if (aopClasses.size() > 0) {
            try {
                for (Class<?> aopClass : aopClasses) {
                    Method[] declaredMethods = aopClass.getDeclaredMethods();
                    if (declaredMethods.length > 0) {
                        for (Method declaredMethod : declaredMethods) {
                            // 判断代理的类型
                            if (declaredMethod.isAnnotationPresent(Around.class)) {
                                Around aroundAnnotation = declaredMethod.getAnnotation(Around.class);
                                // 获取execution表达式
                                String execution = aroundAnnotation.execution();
                                // 解析表达式
                                int lastIndex = execution.lastIndexOf(".");
                                // 获取类路径
                                String proxyClassPath = execution.substring(0, lastIndex);
                                // 获取收代理的方法
                                String proxyMethod = execution.substring(lastIndex + 1);
                                // 根据代理类路径生成类
                                Class<?> targetClass = Class.forName(proxyClassPath);
                                proxyClassSet.add(targetClass);
                                // 获取代理类的对象
                                List<Object> targetObjects = iocClassesContainer.get(targetClass);
                                if (targetObjects.size() > 1) {
                                    throw new ToMuchBeanExistException("");
                                } else if (targetObjects.size() == 0) {
                                    throw new BeanNotMatchException("");
                                }
                                Object targetObject = targetObjects.get(0);
                                // 在生成代理类之前需要先注入依赖，避免代理类无法注入
                                this.doInjectionOneClass(targetClass);
                                // 生成代理类
                                CGLIBProxy cglibProxy = new CGLIBProxy(targetClass, proxyMethod, aopClass, declaredMethod, targetObject);
                                Object proxyInstance = cglibProxy.getProxyInstance();

                                updateIOCContainer(proxyInstance, targetClass);
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 将代理对象替换原本的对象
     * @param proxyInstance 代理对象
     * @param targetClass 目标类
     */
    private void updateIOCContainer(Object proxyInstance, Class<?> targetClass) {
        // 替换类IOC容器中的值
        List<Object> list = new ArrayList<>();
        list.add(proxyInstance);
        iocClassesContainer.put(targetClass, list);
        // 替换名称IOC容器中的值
        Service service = targetClass.getAnnotation(Service.class);
        Controller controller = targetClass.getAnnotation(Controller.class);
        Repository repository = targetClass.getAnnotation(Repository.class);
        Component component = targetClass.getAnnotation(Component.class);
        String simpleName = "";
        if (controller != null) {
            simpleName = controller.value();
        } else if (service != null) {
            simpleName = service.value();
        } else if (component != null) {
            simpleName = component.value();
        } else if (repository != null) {
            simpleName = repository.value();
        }
        if (!simpleName.equals("")) {
            iocNameContainer.put(simpleName, proxyInstance);
        }
        // 替换接口IOC容器中的值
        Class<?>[] interfaces = targetClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            List<Object> beans = iocInterfacesContainer.get(anInterface);
            if (beans != null) {
                for (int i = 0; i < beans.size(); i++) {
                    Object obj = beans.get(i);
                    if (obj.getClass() == targetClass) {
                        beans.set(i, proxyInstance);
                        break;
                    }
                }
            }
            iocInterfacesContainer.put(anInterface, beans);
        }
    }

    /**
     * 进行依赖注入
     */
    private void doInjection() {
        Set<Class<?>> set = iocClassesContainer.keySet();
        for (Class<?> componentClass : set) {
            if (!proxyClassSet.contains(componentClass)) {
                doInjectionOneClass(componentClass);
            }
        }
    }

    /**
     * 给单个类注入依赖
     * @param componentClass 指定类
     */
    private void doInjectionOneClass(Class<?> componentClass) {
        // 获取该类的属性
        Field[] declaredFields = componentClass.getDeclaredFields();
        if (declaredFields.length != 0) {
            for (Field declaredField : declaredFields) {
                Object componentObj = null;
                // 判断是否带有自动注入的注解
                boolean ifAutowired = declaredField.isAnnotationPresent(Autowired.class);
                boolean ifQualifier = declaredField.isAnnotationPresent(Qualifier.class);
                if (ifAutowired) {
                    // 能够根据type自动注入
                    List<Object> components = this.iocClassesContainer.get(declaredField.getType());
                    if (components == null) {
                        components = this.iocInterfacesContainer.get(declaredField.getType());
                    }
                    if (components.size() > 1) {
                        if (ifQualifier) {
                            // 有多个匹配的结果，通过name进行匹配
                            Qualifier qualifierAnnotation = componentClass.getAnnotation(Qualifier.class);
                            String name = qualifierAnnotation.value();
                            componentObj = iocNameContainer.get(name);
                        } else {
                            // 不能通过名称匹配则报错
                            throw new ToMuchBeanExistException("有多个Bean符合要求，这样是被禁止的");
                        }
                    } else if (components.size() == 1) {
                        componentObj = components.get(0);
                    } else {
                        throw new BeanNotMatchException("没有Bean符合要求被匹配");
                    }
                }

                // 通过反射注入依赖
                declaredField.setAccessible(true);
                try {
                    declaredField.set(iocClassesContainer.get(componentClass).get(0), componentObj);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
