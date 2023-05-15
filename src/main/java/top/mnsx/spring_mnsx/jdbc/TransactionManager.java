package top.mnsx.spring_mnsx.jdbc;

import java.sql.Connection;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/7 16:14
 * @Description: 事务管理者
 */
public class TransactionManager {
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 将链接存储ThreadLocal
    private static void init() {
        Connection connection = getConnection();
        threadLocal.set(connection);
    }

    // 保证相同线程获取相同的链接
    public static Connection getThreadConnection() {
        init();
        return threadLocal.get();
    }

    // 获取链接通过链接工厂
    private static Connection getConnection() {
        return JdbcFactory.getConnection();
    }
}
