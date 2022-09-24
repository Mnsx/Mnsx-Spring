package top.mnsx.demo.test.dao;

import top.mnsx.demo.dao.UserDao;
import top.mnsx.demo.dao.impl.UserDaoImpl;
import top.mnsx.demo.entity.User;
import top.mnsx.my_spring.annotation.Autowired;
import top.mnsx.my_spring.annotation.bean.Component;
import top.mnsx.my_spring.container.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.test.dao
 * @CreateTime: 2022/9/1
 * @Description:
 */
public class UserDaoTest {
    public static void testSelectAll() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBeans(UserDao.class);
        List<User> users = userDao.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void testInsertOne() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBeans(UserDao.class);
        boolean flag = userDao.insertOne("xkq", "123123", 0.0);
        System.out.println(flag);
    }

    public static void testDeleteOne() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBeans(UserDao.class);
        System.out.println(userDao.deleteOne(2));
    }

    public static void testSelectOne() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBeans(UserDao.class);
        User user = userDao.selectOne(1);
        System.out.println(user);
    }

    public static void testUpdateOne() {
        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        context.bootStrap("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBeans(UserDao.class);
        System.out.println(userDao.updateOne(5, "ysy", "123123", 0.0));
    }

    public static void main(String[] args) {
        testUpdateOne();
    }
}
