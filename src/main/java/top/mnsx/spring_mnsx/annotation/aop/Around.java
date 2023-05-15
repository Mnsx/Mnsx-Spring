package top.mnsx.spring_mnsx.annotation.aop;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/6 15:08
 * @Description: 环绕代理
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Around {
    String execution() default "";
}
