package top.mnsx.spring.study.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class Bean1 implements DisposableBean {

    public Bean1() {
        System.out.println("Bean1 create");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("bean1 destroy");
    }
}
