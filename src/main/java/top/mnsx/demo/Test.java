package top.mnsx.demo;

import top.mnsx.demo.TestController;
import top.mnsx.demo.TestRepository;
import top.mnsx.demo.TestService;
import top.mnsx.spring_mnsx.container.ClassPathXmlApplication;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 14:38
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplication context = ClassPathXmlApplication.getContext();
        context.bootStrap("applicationContext.xml");

        TestController bean = (TestController) context.getBean(TestController.class);
        bean.test();
    }
}
