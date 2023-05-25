package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.annotation.EnvConditional;

@Configuration
@EnvConditional(EnvConditional.Env.PROD)
public class ProdBeanConfig {
    @Bean
    public String name() {
        return "prod";
    }
}
