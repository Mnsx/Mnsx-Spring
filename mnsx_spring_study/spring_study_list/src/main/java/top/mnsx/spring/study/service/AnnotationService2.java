package top.mnsx.spring.study.service;

import top.mnsx.spring.study.annotation.TypeParameterAnnotation;
import top.mnsx.spring.study.annotation.TypeUseAnnotation;

import java.util.Map;

@TypeUseAnnotation("声明在类上")
public class AnnotationService2<@TypeUseAnnotation("声明在类泛型参数上") P1> {
    private Map<@TypeUseAnnotation("声明在类的泛型参数类型上")String, Integer> map;

    public <@TypeParameterAnnotation("声明在方法泛型参数上") P2> void m1() {
    }
}
