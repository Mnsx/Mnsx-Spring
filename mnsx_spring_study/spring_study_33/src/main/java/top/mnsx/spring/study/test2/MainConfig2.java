package top.mnsx.spring.study.test2;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import top.mnsx.spring.study.test1.Service1;

public class MainConfig2 {
    @Bean
    public Service1 service1() {
        return new Service1();
    }

    @Bean
    public Advisor interceptor1() {
        MethodBeforeAdvice advice = (method, args, target) -> {
            System.out.println("准备调用" + method);
        };
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice);
        return advisor;
    }

    @Bean
    public MethodInterceptor interceptor2() {
        return (MethodInterceptor) (invocation) -> {
            long startTime = System.nanoTime();
            Object result = invocation.proceed();
            long endTime = System.nanoTime();
            System.out.println(invocation.getMethod() + "耗时：" + (endTime - startTime));
            return result;
        };
    }

    @Bean
    public ProxyFactoryBean serviceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetName("service1");
        proxyFactoryBean.setInterceptorNames("interceptor*");
        return proxyFactoryBean;
    }
}
