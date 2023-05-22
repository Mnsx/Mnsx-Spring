package top.mnsx.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mnsx.spring.study.component.Component1;

@Component
public class BeanService {
    @Autowired
    private Component1 component1;

    public void test() {
        System.out.println(component1);
    }
}
