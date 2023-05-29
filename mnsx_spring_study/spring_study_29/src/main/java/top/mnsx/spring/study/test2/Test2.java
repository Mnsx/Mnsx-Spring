package top.mnsx.spring.study.test2;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test2 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig2.class);
        context.refresh();
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.println("beanName:" + beanName  + ", bean: " + bean);
        });
    }
}
