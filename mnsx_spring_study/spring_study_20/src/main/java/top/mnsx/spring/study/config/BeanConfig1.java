package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.condition.OnMissingBeanCondition;
import top.mnsx.spring.study.service.IService;
import top.mnsx.spring.study.service.Service1;

@Configuration
public class BeanConfig1 {
    @Conditional(OnMissingBeanCondition.class)
    @Bean
    public IService service() {
        return new Service1();
    }
}
