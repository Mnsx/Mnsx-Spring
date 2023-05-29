package test1;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SendMsgThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
        System.out.println("异常警报：");
        System.out.println("method: " + method.toGenericString()
                + ", args: " + Arrays.stream(args).collect(Collectors.toList()));
        System.out.println(e.getMessage());
        System.out.println("清尽快修复Bug！");
    }
}
