package top.mnsx.spring.study.anno;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import top.mnsx.spring.study.bean.MyScopeBean;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(MyScopeBean.SCOPE_MY)
public @interface MyScope2 {
    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
