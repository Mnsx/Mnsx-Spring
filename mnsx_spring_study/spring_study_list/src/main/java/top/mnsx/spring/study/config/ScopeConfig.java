package top.mnsx.spring.study.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ScopeConfig {
//    @Bean
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();

        Map<String, Object> map = new HashMap<>();
        map.put(ThreadScope.THREAD_SCOPE, new ThreadScope());

        customScopeConfigurer.setScopes(map);
        return customScopeConfigurer;
    }
}
