package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.condition.MyCondition1;

@Configuration
public class MainConfig2 {
    @Conditional(MyCondition1.class)
    @Bean
    public String name() {
        return "mnsx";
    }

    @Bean
    public String address() {
        return "cdc";
    }
}
