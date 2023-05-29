package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceA {
    public void m1() {
        System.out.println("serviceB m1");
    }

    private ServiceB serviceB;

    @Autowired
    private void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
