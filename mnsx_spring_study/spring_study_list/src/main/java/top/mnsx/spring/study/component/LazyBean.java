package top.mnsx.spring.study.component;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class LazyBean {
    public LazyBean() {
        System.out.println("LazyBean create");
    }
}
