package top.mnsx.spring.study.config;

import top.mnsx.spring.study.service.ServiceA;
import top.mnsx.spring.study.service.ServiceB;

//@Configuration
public class BeanConfig1 {
//    @Bean
    public ServiceA serviceA() {
        System.out.println("调用ServiceA方法");
        return new ServiceA();
    }

//    @Bean
    public ServiceB serviceB1() {
        System.out.println("调用ServiceB1方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }

//    @Bean
    public ServiceB serviceB2() {
        System.out.println("调用ServiceB2方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }
}
