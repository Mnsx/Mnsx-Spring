package top.mnsx.spring.study.config;

import top.mnsx.spring.study.component.Component1;

//@Configuration
public class BeanConfig {
//    @Bean
    public Component1 component1() {
        return new Component1();
    }
}
