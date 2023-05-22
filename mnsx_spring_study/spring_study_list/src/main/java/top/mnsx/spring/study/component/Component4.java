package top.mnsx.spring.study.component;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("thread")
@Component
public class Component4 {
    public void success() {
        System.out.println("success");
    }
}
