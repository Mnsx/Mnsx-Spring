package top.mnsx.spring.study.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class Bean3 implements DisposableBean {

    public Bean3() {
        System.out.println("Bean3 create");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("bean3 destroy");
    }
}
