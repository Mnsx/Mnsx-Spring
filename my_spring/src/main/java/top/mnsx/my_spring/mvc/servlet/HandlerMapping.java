package top.mnsx.my_spring.mvc.servlet;

import java.lang.reflect.Method;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.handler
 * @CreateTime: 2022/8/26
 * @Description:
 */
public class HandlerMapping {
    private Object controlObject;
    private Method method;

    public HandlerMapping(Object controlObject, Method method) {
        this.controlObject = controlObject;
        this.method = method;
    }

    public Object getControlObject() {
        return controlObject;
    }

    public void setControlObject(Object controlObject) {
        this.controlObject = controlObject;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "HandlerMapping{" +
                "controlObject=" + controlObject +
                ", method=" + method +
                '}';
    }
}
