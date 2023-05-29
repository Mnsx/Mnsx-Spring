package top.mnsx.spring.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import top.mnsx.spring.study.domain.User;
import top.mnsx.spring.study.mapper.UserMapper;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
public class Test1 {
    @Resource
    private UserMapper userMapper;

/*    @Test
    public void test1() {
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(datasource);
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        try {
            System.out.println(userMapper.selectList(null));
            User user1 = new User();
            user1.setId(0);
            user1.setName("mnsx");
            userMapper.insert(user1);
            User user2 = new User();
            user2.setName("xkq");
            userMapper.insert(user2);

            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
        }

        System.out.println(userMapper.selectList(null));
    }

    @Test
    public void test2() {
        PlatformTransactionManager  platformTransactionManager = new DataSourceTransactionManager(dataSource);
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(10);
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager, transactionDefinition);
        transactionTemplate.executeWithoutResult((transactionStatus -> {
            System.out.println(userMapper.selectList(null));
            User user1 = new User();
            user1.setId(0);
            user1.setName("mnsx");
            userMapper.insert(user1);
            User user2 = new User();
            user2.setName("xkq");
            userMapper.insert(user2);
        }));
        System.out.println(userMapper.selectList(null));
    }*/
}
