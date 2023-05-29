package top.mnsx.spring.study.service;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MethodBeforeInterceptor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("serviceA".equals(beanName)) {
            //代理创建工厂，需传入被代理的目标对象
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            //添加一个方法前置通知，会在方法执行之前调用通知中的before方法
            proxyFactory.addAdvice(new MethodBeforeAdvice() {
                @Override
                public void before(Method method, Object[] args, Object target) throws Throwable {
                    System.out.println("hello serviceA");
                }
            });
            return proxyFactory.getProxy();
        }
        return bean;
    }
}
