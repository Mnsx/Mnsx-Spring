package top.mnsx.spring.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import top.mnsx.spring.study.bean.AwareBean;
import top.mnsx.spring.study.bean.Bean1;
import top.mnsx.spring.study.domain.Car;
import top.mnsx.spring.study.domain.CompositeObj;
import top.mnsx.spring.study.domain.Person;
import top.mnsx.spring.study.domain.User;
import top.mnsx.spring.study.processor.MyDestructionAwareBeanPostProcessor;
import top.mnsx.spring.study.processor.MySmartInstantiationAwareBeanPostProcessor;
import top.mnsx.spring.study.service.Service1;
import top.mnsx.spring.study.service.ServiceA;
import top.mnsx.spring.study.service.ServiceB;

import javax.swing.*;
import java.util.Arrays;

@SpringBootTest
public class SpringStudyTest {
    @Test
    public void test1() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        System.out.println(beanDefinition);
    }

    @Test
    public void test2() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        builder.addPropertyValue("name", "bmw");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        System.out.println(beanDefinition);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("car", beanDefinition);
        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);
    }

    @Test
    public void test3() {
        BeanDefinitionBuilder carBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        AbstractBeanDefinition carBeanDefinition = carBuilder.getBeanDefinition();
        AbstractBeanDefinition userBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class.getName())
                .addPropertyValue("name", "Mnsx")
                .addPropertyReference("car", "car")
                .getBeanDefinition();
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("car", carBeanDefinition);
        beanFactory.registerBeanDefinition("user", userBeanDefinition);
        System.out.println(beanFactory.getBean("car"));
        System.out.println(beanFactory.getBean("user"));
    }

    @Test
    public void test4() {
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition(Car.class)
                .addPropertyValue("name", "bmw")
                .getBeanDefinition();

        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition()
                .setParentName("car1")
                .getBeanDefinition();

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        factory.registerBeanDefinition("car2", carBeanDefinition2);
        System.out.println(factory.getBean("car1"));
        System.out.println(factory.getBean("car2"));
    }

    @Test
    public void test5() {
        ManagedList<String> stringList = new ManagedList<>();
        stringList.addAll(Arrays.asList("Mnsx", "mnsx", "MNSX"));

        ManagedSet<String> stringSet = new ManagedSet<>();
        stringSet.addAll(Arrays.asList("Mnsx", "mnsx", "MNSX"));

        ManagedMap<String, String> stringMap = new ManagedMap<>();
        stringMap.put("1", "Mnsx");
        stringMap.put("2", "mnsx");
        stringMap.put("3", "MNSX");

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(CompositeObj.class.getName());
        beanDefinition.getPropertyValues().add("stringList", stringList)
                .add("stringSet", stringSet)
                .add("stringMap", stringMap);

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("compositeObj", beanDefinition);
        System.out.println(factory.getBean("compositeObj"));
    }

    @Test
    public void test6() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(factory);
        reader.register(Service1.class);
        System.out.println(factory.getBean("service1"));
    }

    @Test
    public void test7() {
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition(Car.class)
                .addPropertyValue("name", "bmw")
                .getBeanDefinition();

        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition()
                .setParentName("car1")
                .getBeanDefinition();

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        factory.registerBeanDefinition("car2", carBeanDefinition2);

        for (String beanName : factory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            BeanDefinition mergedBeanDefinition = factory.getMergedBeanDefinition(beanName);
            System.out.println(beanName);
            System.out.println(beanDefinition);
            System.out.println(mergedBeanDefinition);
        }
    }

    @Test
    public void test8() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new MySmartInstantiationAwareBeanPostProcessor());
        factory.registerBeanDefinition("name", BeanDefinitionBuilder
                .genericBeanDefinition(String.class)
                .addConstructorArgValue("mnsx")
                .getBeanDefinition());
        factory.registerBeanDefinition("age", BeanDefinitionBuilder
                .genericBeanDefinition(Integer.class)
                .addConstructorArgValue(20)
                .getBeanDefinition());
        factory.registerBeanDefinition("person",
                BeanDefinitionBuilder
                        .genericBeanDefinition(Person.class)
                        .getBeanDefinition());
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test9() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                if ("user1".equals(beanName)) {
                    if (pvs == null) {
                        pvs = new MutablePropertyValues();
                    }
                    if (pvs instanceof MutablePropertyValues) {
                        MutablePropertyValues mpvs = (MutablePropertyValues) pvs;
                        mpvs.add("name", "mnsx");
                        mpvs.add("age", 18);
                    }
                }
                return null;
            }
        });

        factory.registerBeanDefinition("user1",
                BeanDefinitionBuilder
                        .genericBeanDefinition(User.class)
                        .getBeanDefinition());

        factory.registerBeanDefinition("user2",
                BeanDefinitionBuilder
                        .genericBeanDefinition(User.class)
                        .addPropertyValue("name", "Mnsx")
                        .addPropertyValue("age", 20)
                        .getBeanDefinition());

        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.println(beanName + ":" + factory.getBean(beanName));
        }
    }

    @Test
    public void test10() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("awareBean", BeanDefinitionBuilder.genericBeanDefinition(AwareBean.class).getBeanDefinition());
        factory.getBean("awareBean");
    }

    @Test
    public void test11() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Bean1.class);
        context.refresh();
    }

    @Test
    public void test12() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        factory.registerBeanDefinition("serviceA", BeanDefinitionBuilder.genericBeanDefinition(ServiceA.class)
                .getBeanDefinition());
        // 触发所有单例Bean的初始化
        factory.preInstantiateSingletons();
        factory.destroySingletons();;
    }

    @Test
    public void test13() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        factory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        factory.registerBeanDefinition("serviceB", BeanDefinitionBuilder.genericBeanDefinition(ServiceB.class)
                .getBeanDefinition());
        factory.preInstantiateSingletons();
        factory.destroySingletons();
    }
}
