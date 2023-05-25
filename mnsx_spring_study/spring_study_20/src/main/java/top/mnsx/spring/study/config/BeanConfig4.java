package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.condition.MyCondition2;
import top.mnsx.spring.study.condition.MyConfigurationCondition;

@Conditional(MyConfigurationCondition.class)
@Configuration
public class BeanConfig4 {
    @Bean
    public String name() {
        return "mnsx";
    }
}
