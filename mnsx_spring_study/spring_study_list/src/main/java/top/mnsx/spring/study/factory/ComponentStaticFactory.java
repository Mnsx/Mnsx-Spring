package top.mnsx.spring.study.factory;

import org.springframework.stereotype.Component;
import top.mnsx.spring.study.component.Component1;
import top.mnsx.spring.study.component.Component2;

@Component
public class ComponentStaticFactory {
    public static Component2 buildUser() {
        Component2 component2 = new Component2();
        return component2;
    }
}
