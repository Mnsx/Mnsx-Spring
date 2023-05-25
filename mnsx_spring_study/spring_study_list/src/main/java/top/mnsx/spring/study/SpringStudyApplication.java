package top.mnsx.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import top.mnsx.spring.study.filter.MyFilter;

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
