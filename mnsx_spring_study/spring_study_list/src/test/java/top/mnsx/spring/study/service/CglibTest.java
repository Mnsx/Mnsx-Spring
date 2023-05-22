package top.mnsx.spring.study.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

@SpringBootTest
public class CglibTest {
    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method);
                return methodProxy.invokeSuper(o, objects);
            }
        });
        ProxyService proxyService = (ProxyService) enhancer.create();
        proxyService.test();
    }

    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyService.class);
        enhancer.setCallback((FixedValue) () -> {
            return "hhh";
        });
        ProxyService proxyService = (ProxyService) enhancer.create();
        System.out.println(proxyService.test());
    }

    @Test
    public void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyService.class);
        enhancer.setCallback(NoOp.INSTANCE);
        ProxyService proxyService = (ProxyService) enhancer.create();
        System.out.println(proxyService.test());
    }

    @Test
    public void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyService.class);
        Callback[] callbacks = {
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println(method);
                    return methodProxy.invokeSuper(o, objects);
                },
                (FixedValue) () -> "Hello world"
        };
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter((method) -> {
            String methodName = method.getName();
            return methodName.startsWith("insert") ? 0 : 1;
        });
        ProxyService proxyService = (ProxyService) enhancer.create();
        proxyService.insert();
        System.out.println(proxyService.get());
    }

    @Test
    public void test5() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyService.class);
        Callback callback = (MethodInterceptor) (o, method, objects, methodProxy) ->{
            System.out.println(method);
            return methodProxy.invokeSuper(o, objects);
        };

        Callback fixedValueCallback = (FixedValue) () -> "Hello world";

        CallbackHelper callbackHelper = new CallbackHelper(ProxyService.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? callback : fixedValueCallback;
            }
        };

        enhancer.setCallbacks(callbackHelper.getCallbacks());
        enhancer.setCallbackFilter(callbackHelper);
        ProxyService proxyService = (ProxyService) enhancer.create();
        proxyService.insert();
        System.out.println(proxyService.get());
    }
}
