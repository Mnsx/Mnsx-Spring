package top.mnsx.spring.study.annotation;

import org.springframework.context.annotation.Import;
import top.mnsx.spring.study.config.ProxyConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ProxyConfiguration.class)
public @interface EnableProxy {
    String mode() default "jdk";
}
