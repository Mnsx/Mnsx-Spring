package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {
    @Bean
    public String name() {
        return "Mnsx";
    }

    @Bean
    public String address() {
        return "cdc";
    }
}
