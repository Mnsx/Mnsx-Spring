package top.mnsx.spring.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import top.mnsx.spring.study.annotation.EnableMethodCostTime;
import top.mnsx.spring.study.service.Service1;

@ComponentScan(basePackageClasses = Service1.class, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Service1.class})
})
@EnableMethodCostTime
public class MainConfig6 {
}
