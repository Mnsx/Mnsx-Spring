package top.mnsx.spring.study.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import top.mnsx.spring.study.component.Component3;

@Component
public class ComponentFactoryBean implements FactoryBean<Component3> {

    @Override
    public Component3 getObject() throws Exception {
        Component3 component3 = new Component3();
        return component3;
    }

    @Override
    public Class<?> getObjectType() {
        return Component3.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
