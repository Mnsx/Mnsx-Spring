package top.mnsx.spring_mnsx.annotation.aop;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/6 15:00
 * @Description: aop代理
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Aspect {
}
