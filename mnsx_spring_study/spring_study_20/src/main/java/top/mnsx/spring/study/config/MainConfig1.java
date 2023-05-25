package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.condition.MyCondition1;

@Conditional(MyCondition1.class)
@Configuration
public class MainConfig1 {
    @Bean
    public String name() {
        return "Mnsx";
    }
}
