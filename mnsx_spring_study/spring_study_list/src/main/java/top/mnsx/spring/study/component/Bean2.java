package top.mnsx.spring.study.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class Bean2 implements DisposableBean {

    public Bean2() {
        System.out.println("Bean2 create");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("bean2 destroy");
    }
}
