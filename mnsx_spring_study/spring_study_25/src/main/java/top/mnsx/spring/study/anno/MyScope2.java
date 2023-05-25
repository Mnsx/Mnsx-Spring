package top.mnsx.spring.study.anno;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("my")
public @interface MyScopeAnno {
    ScopedProxyMode proxyMode() default ScopedProxyMode.DEFAULT;
}
