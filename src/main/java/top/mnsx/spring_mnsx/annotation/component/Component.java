package top.mnsx.spring_mnsx.annotation.component;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 18:14
 * @Description: 基础组件类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface Component {
    String value() default "";
}
