package top.mnsx.spring.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import top.mnsx.spring.study.annotation.EnableProxy;
import top.mnsx.spring.study.component.*;
import top.mnsx.spring.study.config.BeanConfig;
import top.mnsx.spring.study.config.BeanConfig1;
import top.mnsx.spring.study.config.ThreadScope;
import top.mnsx.spring.study.domain.ProxyMode;
import top.mnsx.spring.study.filter.MyFilter;
import top.mnsx.spring.study.proxy.TestProxy;
import top.mnsx.spring.study.service.BeanService;
import top.mnsx.spring.study.service.NormalAnnoService;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

@ComponentScan(
        basePackages = {"top.mnsx.spring.study"},
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)
        }
)
@SpringBootApplication
public class SpringStudyApplication {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringStudyApplication.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            if ("serviceA".equals(beanName)) {
                System.out.println(beanName + "->" + context.getBean(beanName));
            }
        }
    }
}
