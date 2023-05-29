package top.mnsx.spring.study.service;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Objects;

public class Test1 {
    @Test
    public void test1() {
        UserService target = new UserService();

        Pointcut pointcut = new Pointcut() {

            @Override
            public ClassFilter getClassFilter() {
                return UserService.class::isAssignableFrom;
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return true;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        if (args.length == 1) {
                            String username = (String) args[0];
                            return username.contains("gf");
                        }
                        return false;
                    }
                };
            }
        };

        MethodBeforeAdvice advice = ((method, args, target1) -> System.out.println("你好" + args[0]));

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        UserService userServiceProxy = (UserService) proxyFactory.getProxy();

        userServiceProxy.work("ysy");
    }
}
