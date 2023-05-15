package top.mnsx.spring_mnsx.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/7 14:16
 * @Description: CGLIB代理
 */
public class CGLIBProxy {
    private Class<?> targetClass;
    private String methodName;
    private Class<?> proxyClass;
    private Method proxyMethod;
    private Object targetObject;

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                if (methodName.equals(method.getName())) {
                    ProceedingJoinPoint proceedingJoinPoint = new ProceedingJoinPoint(method, targetObject, objects);
                    result = proxyMethod.invoke(proxyClass.newInstance(), proceedingJoinPoint);
                } else {
                    result = methodProxy.invokeSuper(o, objects);
                }
                return result;
            }
        });
        return enhancer.create();
    }

    public CGLIBProxy(Class<?> targetClass, String methodName, Class<?> proxyClass, Method proxyMethod, Object targetObject) {
        this.targetClass = targetClass;
        this.methodName = methodName;
        this.proxyClass = proxyClass;
        this.proxyMethod = proxyMethod;
        this.targetObject = targetObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getProxyClass() {
        return proxyClass;
    }

    public void setProxyClass(Class<?> proxyClass) {
        this.proxyClass = proxyClass;
    }

    public Method getProxyMethod() {
        return proxyMethod;
    }

    public void setProxyMethod(Method proxyMethod) {
        this.proxyMethod = proxyMethod;
    }
}
