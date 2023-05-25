package top.mnsx.spring.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.spring.study.service.UserService;

@SpringBootTest
public class SpringStudyTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        System.out.println(userService.getDao());
    }
}
