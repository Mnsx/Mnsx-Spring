package top.mnsx.demo.test.service;

import top.mnsx.demo.entity.User;
import top.mnsx.demo.service.UserService;
import top.mnsx.demo.service.impl.UserServiceImpl;
import top.mnsx.my_spring.container.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.test.service
 * @CreateTime: 2022/9/1
 * @Description:
 */
public class UserServiceTest {
    public static UserService init() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserService userService = (UserService) context.getBeans(UserService.class);
        return userService;
    }

    public static void testFindAll() {
        UserService userService = init();
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void testAddUser() {
        UserService userService = init();
        System.out.println(userService.addUser("xkq", "123123", 0.0));
    }

    public static void testRemoveUser() {
        UserService userService = init();
        System.out.println(userService.removeUser(6));
    }

    public static void testFindUser() {
        UserService userService = init();
        System.out.println(userService.findUser(6));
    }

    public static void testModifyUser() {
        UserService userService = init();
        System.out.println(userService.modifyUser(6, "ysy", "123123", 0.0));
    }

    public static void main(String[] args) {
        testRemoveUser();
    }
}
