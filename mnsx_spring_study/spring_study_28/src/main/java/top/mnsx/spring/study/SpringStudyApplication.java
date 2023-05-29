package top.mnsx.spring.study;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.mnsx.spring.study.service.MainConfig;

@SpringBootApplication
public class SpringStudyApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addBeanFactoryPostProcessor(beanFactory -> {
            if (beanFactory instanceof DefaultListableBeanFactory) {
                ((DefaultListableBeanFactory) beanFactory).setAllowRawInjectionDespiteWrapping(true);
            }
        });
        context.register(MainConfig.class);
        context.refresh();
    }
}
