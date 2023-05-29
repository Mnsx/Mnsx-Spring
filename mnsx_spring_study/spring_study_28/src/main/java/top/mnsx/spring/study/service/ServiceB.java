package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceB {
    public void m1() {
        System.out.println("ServiceB m1");
        this.serviceA.m1();
    }

    private ServiceA serviceA;

    @Autowired
    private void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public ServiceA getServiceA() {
        return serviceA;
    }
}
