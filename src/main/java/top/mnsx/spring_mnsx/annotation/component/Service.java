package top.mnsx.spring_mnsx.annotation.component;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 18:16
 * @Description: 逻辑层组件
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Service {
    String value() default "";
}
