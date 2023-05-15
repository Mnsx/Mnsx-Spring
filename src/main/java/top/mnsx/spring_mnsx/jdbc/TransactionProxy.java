package top.mnsx.spring_mnsx.jdbc;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.mnsx.spring_mnsx.annotation.aop.Aspect;
import top.mnsx.spring_mnsx.aop.ProceedingJoinPoint;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/6 15:23
 * @Description: 事务代理
 */
@Slf4j
public class TransactionProxy {
    private final Class<?> targetClass;
    private final List<String> transactionalMethods;

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                if (transactionalMethods.contains(method.getName())) {
                    Connection connection = TransactionManager.getThreadConnection();
                    // 关闭自动提交
                    connection.setAutoCommit(false);
                    try {
                        result = methodProxy.invokeSuper(o, objects);
                        // 如果没有异常就提交
                        connection.commit();
                    } catch (RuntimeException e) {
                        // 如果出现异常回退
                        connection.rollback();
                    } finally {
                        // 回收线程，防止线程池中没有线程
                        JdbcFactory.closeDataSource(connection);
                    }
                } else {
                    result = method.invoke(targetClass.newInstance(), objects);
                }
                return result;
            }
        });
        return enhancer.create();
    }

    public TransactionProxy(Class<?> targetClass, List<String> transactionalMethods) {
        this.targetClass = targetClass;
        this.transactionalMethods = transactionalMethods;
    }
}
