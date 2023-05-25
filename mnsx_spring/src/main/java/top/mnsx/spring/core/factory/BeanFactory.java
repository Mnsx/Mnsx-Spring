package top.mnsx.spring.core.factory;

import top.mnsx.spring.core.domain.BeanDefinition;
import top.mnsx.spring.core.loader.PropertiesLoader;
import top.mnsx.spring.core.register.BeanRegister;

import java.util.Map;

/**
 * bean工厂，负责制造和注册bean
 */
public class BeanFactory {
    // bean注册中心
    private final BeanRegister beanRegister;

    // Bean信息Map
    private final Map<String, BeanDefinition> beanDefinitionMap;

    public BeanFactory() {
        this.beanRegister = new BeanRegister();
        this.beanDefinitionMap = new PropertiesLoader().loadSource();
    }

    /**
     * 获取Bean
     * @param beanName bean的名称
     * @return Object
     */
    public Object getBean(String beanName) {
        Object bean = beanRegister.getSingletonBean(beanName);
        return bean == null ? createBean(beanDefinitionMap.get(beanName)) : bean;
    }

    /**
     * 创建Bean
     * @param beanDefinition bean信息对象
     * @return Object
     */
    private Object createBean(BeanDefinition beanDefinition) {
        try {
            Object beanObj = beanDefinition.getBeanClass().newInstance();
            beanRegister.registerSingletonBean(beanDefinition.getBeanName(), beanObj);
            return beanObj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
