package top.mnsx.spring.study.test3;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.mnsx.spring.study.test1.MainConfig1;

public class Test3 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig3.class);
        context.refresh();

        System.out.println(context.getBean(LessonModel.class));
    }
}
