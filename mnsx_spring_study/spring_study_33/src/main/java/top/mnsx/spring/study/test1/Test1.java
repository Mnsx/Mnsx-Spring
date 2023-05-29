package top.mnsx.spring.study.test1;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        Service1 serviceProxy = context.getBean("serviceProxy", Service1.class);
        System.out.println("----------------");
        serviceProxy.m1();
        System.out.println("----------------");
        serviceProxy.m2();
    }
}
