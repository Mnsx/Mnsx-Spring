package top.mnsx.spring_mnsx.aop;

import java.lang.reflect.Method;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/6 15:25
 * @Description: 反射调用方法
 */
public class ProceedingJoinPoint {
    private Method method;
    private Object targetObject;
    private Object[] args;
    
    public Object proceed() {
        try {
            return method.invoke(targetObject, args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ProceedingJoinPoint(Method method, Object targetObject, Object[] args) {
        this.method = method;
        this.targetObject = targetObject;
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
