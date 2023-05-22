package top.mnsx.spring.study.service;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Service;
import top.mnsx.spring.study.annotation.AnnotationA;
import top.mnsx.spring.study.annotation.AnnotationB;

import java.util.Arrays;

@AnnotationB(value = "b", annotationAValue = "hhh")
@Service
public class NormalAnnoService {
    public void test1() {
        System.out.println(NormalAnnoService.class.getAnnotation(AnnotationB.class));
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(NormalAnnoService.class, AnnotationA.class));
    }
}
