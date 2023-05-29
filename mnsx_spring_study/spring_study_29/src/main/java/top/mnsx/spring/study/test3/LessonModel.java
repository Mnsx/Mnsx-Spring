package top.mnsx.spring.study.test3;

import org.springframework.stereotype.Component;

@Component
public class LessonModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LessonModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
