package top.mnsx.spring.study.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.mnsx.spring.study.bean.MyScopeBean;

import java.util.HashMap;

@Configuration
@ComponentScan("top.mnsx.spring.study")
public class ScopeConfig {
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        HashMap<String, Object> map = new HashMap<>();
        map.put(MyScopeBean.SCOPE_MY, new MyScopeBean());
        customScopeConfigurer.setScopes(map);
        return customScopeConfigurer;
    }
}
