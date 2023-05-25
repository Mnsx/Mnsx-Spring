package top.mnsx.spring.study.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig2 {
    @Bean
    public MessageSource messageSource() {
        return new MessageSourceFromDb();
    }
}
