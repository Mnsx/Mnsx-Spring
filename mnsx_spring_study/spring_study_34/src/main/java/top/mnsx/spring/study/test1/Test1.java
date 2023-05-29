package top.mnsx.spring.study.test1;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class Test1 {
    @Test
    public void test1() {
        try {
            Service1 target = new Service1();
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
            proxyFactory.setTarget(target);
            proxyFactory.addAspect(Aspect1.class);
            Service1 proxy = proxyFactory.getProxy();
            proxy.m1();
            proxy.m2();
        } catch (Exception e) {
        }
    }
}
