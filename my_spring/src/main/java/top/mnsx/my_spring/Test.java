package top.mnsx.my_spring;

import org.apache.ibatis.javassist.ClassPath;
import org.springframework.web.bind.annotation.RequestParam;
import top.mnsx.my_spring.container.ClassPathXmlApplicationContext;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring
 * @CreateTime: 2022/7/10
 * @Description: 启动类
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
    }
}
