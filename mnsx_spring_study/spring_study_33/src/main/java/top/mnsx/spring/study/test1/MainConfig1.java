package top.mnsx.spring.study.test1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class MainConfig1 {
    @Bean
    public Service1 service1() {
        return new Service1();
    }

    @Bean
    public MethodBeforeAdvice beforeAdvice() {
        return (method, args, target) -> System.out.println("准备调用" + method);
    }

    @Bean
    public MethodInterceptor costTimeInterceptor() {
        return (invocation -> {
            long startTime = System.nanoTime();
            Object result = invocation.proceed();
            long endTime = System.nanoTime();
            System.out.println(invocation.getMethod() + "耗时：" + (endTime - startTime));
            return result;
        });
    }

    @Bean
    public ProxyFactoryBean serviceProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetName("service1");
        proxyFactoryBean.setInterceptorNames("beforeAdvice", "costTimeInterceptor");
        return proxyFactoryBean;
    }
}
