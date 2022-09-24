package top.mnsx.demo.dao.impl;

import top.mnsx.demo.dao.UserDao;
import top.mnsx.demo.entity.User;
import top.mnsx.my_spring.annotation.Autowired;
import top.mnsx.my_spring.annotation.bean.Repository;
import top.mnsx.my_spring.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.dao.impl
 * @CreateTime: 2022/9/1
 * @Description:
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> selectAll() {
        List<User> users = jdbcTemplate.query("select * from user", User.class);
        return users;
    }

    @Override
    public Boolean insertOne(String name, String password, Double balance) {
        int result = jdbcTemplate.update("insert into user (name, password, balance) values(?, ?, ?)", name, password == null ? "123456" : password, balance);
        return result == 1;
    }

    @Override
    public Boolean deleteOne(Integer id) {
        int result = jdbcTemplate.update("delete from user where id = ?", id);
        return result == 1;
    }

    @Override
    public User selectOne(Integer id) {
        User user = jdbcTemplate.queryForObject("select * from user where id = ?", User.class, id);
        return user;
    }

    @Override
    public Boolean updateOne(Integer id, String name, String password, Double balance) {
        int result = jdbcTemplate.update("update user set name = ?, password = ?, balance = ? where id = ?", name, password, balance, id);
        return result == 1;
    }
}
