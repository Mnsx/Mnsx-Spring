package top.mnsx.spring.study.domain;

import org.springframework.stereotype.Component;
import top.mnsx.spring.study.anno.MyScope2;

import java.util.UUID;

@Component
@MyScope2
public class User2 {
    private String name;

    public User2() {
        this.name = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
