package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.service.Service3;

@Configuration
public class BeanConfig3 {
    @Bean
    public Service3 service3() {
        return new Service3();
    }
}
