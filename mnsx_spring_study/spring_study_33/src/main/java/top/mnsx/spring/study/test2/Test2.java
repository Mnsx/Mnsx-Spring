package top.mnsx.spring.study.test2;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.mnsx.spring.study.test1.MainConfig1;
import top.mnsx.spring.study.test1.Service1;

public class Test2 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        Service1 serviceProxy = context.getBean("serviceProxy", Service1.class);
        System.out.println("----------------");
        serviceProxy.m1();
        System.out.println("----------------");
        serviceProxy.m2();
    }
}
