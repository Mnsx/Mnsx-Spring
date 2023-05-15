package top.mnsx.spring_mnsx.jdbc;

import top.mnsx.spring_mnsx.container.parser.XmlSpringConfigParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/7 16:16
 * @Description: jdbc链接工程
 */
public class JdbcFactory {
    // XML中配置的数据库相关
    private static Map<String, String> dataSource = XmlSpringConfigParser.getDataSource("applicationContext.xml");
    // 连接池
    private static LinkedList<Connection> pool = new LinkedList<>();
    // 锁，防止多线程问题
    private static Lock lock = new ReentrantLock();
    // 条件
    private static Condition condition = lock.newCondition();

    static {
        // 加载JDBC驱动
        try {
            Class.forName(dataSource.get("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 链接池中默认链接个数
        Integer initConnections = 10;
        String init_count = dataSource.get("init_count");
        // 如果XML中配置了，那么使用用户配置参数
        if (init_count != null) {
            initConnections = Integer.parseInt(init_count);
        }
        // 循环创建链接，存放到线程池中
        for (int i = 0; i < initConnections; ++i) {
            try {
                Connection connection = DriverManager.getConnection(
                        dataSource.get("url"),
                        dataSource.get("name"),
                        dataSource.get("password"));
                pool.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public JdbcFactory() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        // 加锁，防止获取线程时，其他线程进入方法，导致多线程问题
        lock.lock();
        try {
            // 循环锁，一直循环知道连接池中有多的链接
            while (pool.size() < 0) {
                try {
                    // 条件等待，等待其他线程归还pool时唤醒
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 如果连接池不为空，那么获取链接
            if (!pool.isEmpty()) {
                connection = pool.removeFirst();
            }
            return connection;
        } finally {
            lock.unlock();
        }
    }

    public static void closeDataSource(Connection connection) {
        if (connection != null) {
            // 枷锁
            lock.lock();
            try {
                // 归还链接
                pool.addLast(connection);
                // 唤醒等待链接的线程
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
