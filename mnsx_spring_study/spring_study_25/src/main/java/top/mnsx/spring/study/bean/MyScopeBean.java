package top.mnsx.spring.study.bean;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import top.mnsx.spring.study.domain.User;

public class MyScopeBean implements Scope {

    public static final String SCOPE_MY = "my";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("MyScopeBean get:" + name);
        User user = (User) objectFactory.getObject();
        user.setName("hhh");
        return user;
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
