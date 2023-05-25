package top.mnsx.spring.study.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import top.mnsx.spring.study.scope.MyScope;

@Component
@Scope(value = "my", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
