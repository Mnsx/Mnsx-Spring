package top.mnsx.spring_mnsx.annotation.injection;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 22:36
 * @Description: 自动注入
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Qualifier {
    String value() default "";
}
