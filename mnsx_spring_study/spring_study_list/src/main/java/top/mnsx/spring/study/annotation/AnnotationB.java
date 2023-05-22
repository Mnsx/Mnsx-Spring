package top.mnsx.spring.study.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@AnnotationA
public @interface AnnotationB {
    String value() default "b";

    @AliasFor(annotation = AnnotationA.class, value = "value")
    String annotationAValue();
}
