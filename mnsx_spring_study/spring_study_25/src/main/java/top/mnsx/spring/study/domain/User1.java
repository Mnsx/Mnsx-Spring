package top.mnsx.spring.study.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import top.mnsx.spring.study.bean.MyScopeBean;

import java.util.UUID;

@Component
@Scope(value = MyScopeBean.SCOPE_MY, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User1 {
    private String name;

    public User1() {
        this.name = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
