package test1;

import org.junit.jupiter.api.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class Test1 {
    /**
     * 前置通知拦截非法访问
     */
    @Test
    public void test1() {
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args, target) -> {
            String username = (String) args[0];
            if (!"unknown".equals(username)) {
                throw new RuntimeException(username + "非法访问！");
            }
        });
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.recharge("unknown", 100);
        proxy.recharge("zzh", 100);
    }

    /**
     * 通过异常通知记录异常
     */
    @Test
    public void test2() {
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        proxyFactory.addAdvice(new SendMsgThrowsAdvice());
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.cashOut("unknown", 2000);
    }
}
