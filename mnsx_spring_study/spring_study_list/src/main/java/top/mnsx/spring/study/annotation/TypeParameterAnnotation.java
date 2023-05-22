package top.mnsx.spring.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {
        ElementType.TYPE_PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeParameterAnnotation {
    String value();
}
