package top.mnsx.spring.study.service;

import top.mnsx.spring.study.annotation.TypeParameterAnnotation;

public class AnnotationService1<@TypeParameterAnnotation("声明在类泛型参数上") P1> {
    public <@TypeParameterAnnotation("声明在方法泛型参数上") P2> void m1() {
    }
}
