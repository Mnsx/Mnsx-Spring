package top.mnsx.spring.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.mnsx.spring.study.config.*;
import top.mnsx.spring.study.service.IService;

import java.util.Map;

@SpringBootTest
public class ConditionTest {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.println(beanName + ":" + bean);
        });
    }

    @Test
    public void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.println(beanName + ":" + bean);
        });
    }

    @Test
    public void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        Map<String, IService> serviceMap = context.getBeansOfType(IService.class);
        serviceMap.forEach((beanName, bean) -> {
            System.out.println(beanName + ":" + bean);
        });
    }

    @Test
    public void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        System.out.println(context.getBean("name"));
    }

    @Test
    public void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.println(beanName + ":" + bean);
        });
    }
}
