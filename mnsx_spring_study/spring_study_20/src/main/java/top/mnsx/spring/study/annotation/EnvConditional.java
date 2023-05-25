package top.mnsx.spring.study.annotation;

import org.springframework.context.annotation.Conditional;
import top.mnsx.spring.study.condition.EnvCondition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Conditional(EnvCondition.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnvConditional {
    enum Env {
        TEST, DEV, PROD
    }

    Env value() default Env.DEV;
}
