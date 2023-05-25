package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({BeanConfig3.class, BeanConfig4.class})
public class MainConfig5 {
}
