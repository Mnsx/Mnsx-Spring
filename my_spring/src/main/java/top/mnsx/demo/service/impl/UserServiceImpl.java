package top.mnsx.demo.service.impl;

import top.mnsx.demo.dao.UserDao;
import top.mnsx.demo.entity.User;
import top.mnsx.demo.service.UserService;
import top.mnsx.my_spring.annotation.Autowired;
import top.mnsx.my_spring.annotation.bean.Service;

import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.service.impl
 * @CreateTime: 2022/9/1
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public Boolean addUser(String name, String password, Double balance) {
        return userDao.insertOne(name, password, balance);
    }

    @Override
    public Boolean removeUser(Integer id) {
        return userDao.deleteOne(id);
    }

    @Override
    public User findUser(Integer id) {
        return userDao.selectOne(id);
    }

    @Override
    public Boolean modifyUser(Integer id, String name, String password, Double balance) {
        return userDao.updateOne(id, name, password, balance);
    }
}
