package top.mnsx.spring.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {MailConfig.class})
public class MainConfig1 {
}
