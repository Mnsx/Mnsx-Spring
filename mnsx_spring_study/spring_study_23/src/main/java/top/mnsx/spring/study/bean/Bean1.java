package top.mnsx.spring.study.bean;

import javax.annotation.PostConstruct;

public class Bean1 {
    @PostConstruct
    public void postConstruct1() {
        System.out.println("postConstruct1()");
    }
}
