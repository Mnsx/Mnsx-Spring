package top.mnsx.spring.study.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class MyScope1 implements Scope {

    public static final String SCOPE_MY = "my";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("MyScope >>> get " + name);
        return objectFactory.getObject();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
