package top.mnsx.spring.study.test2;

import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;
import top.mnsx.spring.study.test1.LazyLoaderTest1;

import java.util.UUID;

public class DispatcherTest1 {
    public static class UserModel {
        private String name;

        public UserModel() {
        }

        public UserModel(String name) {
            this.name = name;
        }

        public void say() {
            System.out.println("hello " + name);
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LazyLoaderTest1.UserModel.class);
        Dispatcher dispatch = new Dispatcher() {

            @Override
            public Object loadObject() throws Exception {
                System.out.println("调用Dispatcher.loadObject()方法");
                return new LazyLoaderTest1.UserModel("Mnsx, " + UUID.randomUUID());
            }
        };
        enhancer.setCallback(dispatch);
        LazyLoaderTest1.UserModel proxy = (LazyLoaderTest1.UserModel) enhancer.create();
        System.out.println("第一次调用say方法");
        proxy.say();
        System.out.println("第二次调用say方法");
        proxy.say();
    }
}
