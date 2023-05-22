package top.mnsx.spring.study.service;

public class ProxyService {
    public String test() {
        System.out.println("test1");
        return "test1";
    }

    public String get() {
        System.out.println("get");
        return "get";
    }

    public String insert() {
        System.out.println("insert");
        return "insert";
    }
}
