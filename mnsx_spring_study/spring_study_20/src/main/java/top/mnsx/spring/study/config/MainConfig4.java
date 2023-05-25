package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DevBeanConfig.class, TestBeanConfig.class, ProdBeanConfig.class})
public class MainConfig4 {
}
