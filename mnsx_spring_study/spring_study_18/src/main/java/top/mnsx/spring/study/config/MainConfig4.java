package top.mnsx.spring.study.config;

import org.springframework.context.annotation.Import;
import top.mnsx.spring.study.registrar.MyImportBeanDefinitionRegistrar;

@Import(MyImportBeanDefinitionRegistrar.class)
public class MainConfig4 {
}
