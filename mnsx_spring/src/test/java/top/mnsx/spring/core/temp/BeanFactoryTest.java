package top.mnsx.spring.core.temp;

import top.mnsx.spring.core.factory.BeanFactory;
import top.mnsx.spring.core.temp.bean.QueryComponent;

public class BeanFactoryTest {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        QueryComponent queryComponent = (QueryComponent) beanFactory.getBean("queryComponent");
        System.out.println(queryComponent.query());

        QueryComponent queryComponent_1 = (QueryComponent) beanFactory.getBean("queryComponent");
        System.out.println(queryComponent_1.query());
    }
}
