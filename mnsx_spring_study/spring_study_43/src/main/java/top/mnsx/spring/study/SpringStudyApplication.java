package top.mnsx.spring.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("top.mnsx.spring.study.mapper")
@SpringBootApplication
public class SpringStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
    }
}
