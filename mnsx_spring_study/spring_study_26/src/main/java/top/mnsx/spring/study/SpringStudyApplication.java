package top.mnsx.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Locale;

@SpringBootApplication
public class SpringStudyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringStudyApplication.class, args);
        System.out.println("---------------------");
        System.out.println(context.getMessage("desc", null, Locale.CHINA));
        System.out.println(context.getMessage("desc", null, Locale.UK));
    }
}
