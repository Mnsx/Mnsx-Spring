package top.mnsx.demo;

import top.mnsx.spring_mnsx.annotation.aop.Around;
import top.mnsx.spring_mnsx.annotation.aop.Aspect;
import top.mnsx.spring_mnsx.aop.ProceedingJoinPoint;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/6 16:00
 * @Description:
 */
@Aspect
public class AopTest {

    @Around(execution = "top.mnsx.demo.TestService.test")
    public Object test(ProceedingJoinPoint proceedingJoinPoint) {
        Object proceed = null;
        try {
            System.out.println("前置");
            proceed = proceedingJoinPoint.proceed();
            System.out.println("后置");
        } catch (RuntimeException e) {
            System.out.println("异常");
        } finally {
            System.out.println("最终");
        }
        return proceed;
    }
}
