package top.mnsx.spring.study.test1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;

public class LazyLoaderTest1 {
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
        enhancer.setSuperclass(UserModel.class);
        LazyLoader lazyLoader = new LazyLoader() {

            @Override
            public Object loadObject() throws Exception {
                System.out.println("调用LazyLoader。loadObject()方法");
                return new UserModel("Mnsx");
            }
        };
        enhancer.setCallback(lazyLoader);
        UserModel proxy = (UserModel) enhancer.create();
        System.out.println("第一次调用");
        proxy.say();
        System.out.println("第二次调用");
        proxy.say();
    }
}
