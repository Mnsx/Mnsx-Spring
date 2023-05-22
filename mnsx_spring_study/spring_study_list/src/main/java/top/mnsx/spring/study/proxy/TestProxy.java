package top.mnsx.spring.study.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class TestProxy<T> {
    private T target;

    public TestProxy(T target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public T getProxyObject() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            // 前置方法
            Object result = method.invoke(target, args);
            // 后置方法
            return result;
        });
    }
}
