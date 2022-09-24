package top.mnsx.demo.service;

import top.mnsx.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Boolean addUser(String name, String password, Double balance);

    Boolean removeUser(Integer id);

    User findUser(Integer id);

    Boolean modifyUser(Integer id, String name, String password, Double balance);
}
