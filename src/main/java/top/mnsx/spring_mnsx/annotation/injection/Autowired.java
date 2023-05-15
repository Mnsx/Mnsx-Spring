package top.mnsx.spring_mnsx.annotation.injection;

import java.lang.annotation.*;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 22:34
 * @Description: 自动注入类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Autowired {
}
