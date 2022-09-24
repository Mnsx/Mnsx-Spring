package top.mnsx.demo.dao;

import top.mnsx.demo.entity.User;

import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.demo.dao
 * @CreateTime: 2022/9/1
 * @Description:
 */
public interface UserDao {
    List<User> selectAll();

    Boolean insertOne(String name, String password, Double balance);

    Boolean deleteOne(Integer id);

    User selectOne(Integer id);

    Boolean updateOne(Integer id, String name, String password, Double balance);
}
