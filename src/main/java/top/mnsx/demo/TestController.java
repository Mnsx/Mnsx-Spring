package top.mnsx.demo;

import top.mnsx.spring_mnsx.annotation.component.Controller;
import top.mnsx.spring_mnsx.annotation.injection.Autowired;

import java.io.Serializable;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 22:23
 * @Description:
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    public void test() {
        testService.test();
        System.out.println("do Controller");
    }
}
