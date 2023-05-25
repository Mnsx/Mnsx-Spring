package top.mnsx.spring.core.domain;

/**
 * Bean的定义类
 */
public class BeanDefinition {
    // bean的名称
    private String beanName;
    // bean的类对象
    private Class<?> beanClass;

    // getter and setter
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
