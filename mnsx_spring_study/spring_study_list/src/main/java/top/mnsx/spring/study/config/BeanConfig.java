package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import top.mnsx.spring.study.component.Component1;
import top.mnsx.spring.study.component.Component2;

//@Configuration
public class BeanConfig {
//    @Bean
    public Component1 component1() {
        return new Component1();
    }
}
