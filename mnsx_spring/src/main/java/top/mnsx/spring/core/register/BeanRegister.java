package top.mnsx.spring.core.register;

import top.mnsx.spring.core.domain.BeanDefinition;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean注册中心
 */
public class BeanRegister {
    // 存放单例bean的容器
    private final ConcurrentHashMap<String, Object> singletonBeanMap = new ConcurrentHashMap<>();

    /**
     * 获取容器中的单例bean
     * @param beanName bean名称
     * @return Object
     */
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    /**
     * 将Bean注册到容器中
     * @param beanName bean名称
     * @param beanObj bean单例对象
     */
    public void registerSingletonBean(String beanName, Object beanObj) {
        if (singletonBeanMap.contains(beanObj)) {
            return;
        }
        singletonBeanMap.put(beanName, beanObj);
    }
}
