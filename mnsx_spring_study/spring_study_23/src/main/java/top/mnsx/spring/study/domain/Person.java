package top.mnsx.spring.study.domain;

import top.mnsx.spring.study.annotation.MyAutowired;

public class Person {
    private String name;
    private Integer age;

    public Person() {
        System.out.println("调用Person()");
    }

    public Person(String name, Integer age) {
        System.out.println("调用Person(String name, Integer age)");
        this.name = name;
        this.age = age;
    }

    @MyAutowired
    public Person(String name) {
        System.out.println("调用Person(String name)");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
