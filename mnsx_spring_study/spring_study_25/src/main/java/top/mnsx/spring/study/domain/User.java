package top.mnsx.spring.study.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import top.mnsx.spring.study.bean.MyScopeBean;

import java.util.UUID;

@Component
@Scope(value = MyScopeBean.SCOPE_MY, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {
    private String name;

    public User() {
        this.name = UUID.randomUUID().toString();
        System.out.println("构造函数创建User对象: " + this.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
