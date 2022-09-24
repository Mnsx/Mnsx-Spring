package top.mnsx.my_spring.mvc.servlet;

import com.alibaba.fastjson.JSON;
import top.mnsx.my_spring.exception.NoReturnMatchException;
import top.mnsx.my_spring.mvc.annotation.CrossOrigin;
import top.mnsx.my_spring.mvc.annotation.RequestParam;
import top.mnsx.my_spring.mvc.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.servlet
 * @CreateTime: 2022/8/27
 * @Description:
 */
public class HandlerAdapter {
    private HandlerMapping handlerMapping;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    private Map<String, Class<?>> requiredParameterMap = new ConcurrentHashMap<>();
    private Map<String, Class<?>> unRequiredParameterMap = new ConcurrentHashMap<>();
    private List<String> parameterNames = new ArrayList<>();

    public void handle() {
        Method controllerMethod = handlerMapping.getMethod();
        Object controllerObject = handlerMapping.getControlObject();
        
        this.getMethodParameterInfo(controllerMethod);
        Object controlObject = handlerMapping.getControlObject();

        //TODO:有待优化
        Object[] args = this.getArgs();

        Object result = null;
        try {
            result = controllerMethod.invoke(controllerObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean annotationPresent = controllerMethod.isAnnotationPresent(ResponseBody.class);
        if (annotationPresent) {
            //判断是否解决跨域问题
            if (controllerMethod.isAnnotationPresent(CrossOrigin.class)) {
                String origin = httpServletRequest.getHeader("Origin");
                httpServletResponse.setHeader("Access-Control-Allow-Origin", origin == null ? "true" : origin);
                httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
                httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
                httpServletResponse.setContentType("application/json;charset=UTF-8");
            }

            this.responseJson(result);
        } else if (result instanceof String) {
            //跳转jsp视图
            this.responseJsp(result);
        } else {
            System.out.println("没有返回值");
        }
    }

    private void responseJsp(Object result) {
        String prefix = "/WEB-INF/jsp/";
        String suffix = ".jsp";

        String jsp = (String) result;
        jsp = jsp.startsWith("/") ? jsp.replace("/", "") : jsp;

        try {
            httpServletRequest.getRequestDispatcher(prefix + jsp + suffix).forward(httpServletRequest, httpServletResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void responseJson(Object result) {
        String json = JSON.toJSONString(result);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        try {
            httpServletResponse.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装参数
     * @return
     */
    private Object[] getArgs() {
        Object[] args = new Object[parameterNames.size()];
        int index = 0;

        for (String parameterName : parameterNames) {
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            String[] values = parameterMap.get(parameterName);
            boolean b = requiredParameterMap.containsKey(parameterName);
            if (b) {
                Class<?> aClass = requiredParameterMap.get(parameterName);
                if (values == null) {
                    httpServletResponse.setContentType("text/html;charset=utf-8");
                    try {
                        httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误，" + parameterName + "必须传入参数值");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    args[index++] = this.convertValue(aClass, values);
                }
            } else {
                Class<?> aClass = unRequiredParameterMap.get(parameterName);
                if (values == null) {
                    args[index++] = convertDefaultValue(aClass);
                } else {
                    args[index++] = this.convertValue(aClass, values);
                }
            }
        }

        return args;
    }

    /**
     * 返回默认值
     * @param aClass
     * @return
     */
    //TODO:不完善
    private Object convertDefaultValue(Class<?> aClass) {
        if (aClass == int.class || aClass == Integer.class || aClass == short.class || aClass == Short.class || aClass == long.class || aClass == Long.class || aClass == byte.class || aClass == Byte.class) {
            return 0;
        } else if (aClass == double.class || aClass == Double.class || aClass == float.class || aClass == Float.class) {
            return 0.0;
        } else if (aClass == HttpServletRequest.class) {
            return httpServletRequest;
        } else if (aClass == HttpServletResponse.class) {
            return httpServletResponse;
        } else {
            return "0";
        }
    }

    private Object convertValue(Class<?> c, String[] values) {
        if (c.isArray()) {
            return null;
        } else {
            return this.convertSingleValue(c, values[0]);
        }
    }

    private Object convertSingleValue(Class<?> c, String value) {
        //TODO: 其他类型参数需要添加
        if (c == int.class || c == Integer.class) {
            return Integer.valueOf(value);
        } else if (c == double.class || c == Double.class) {
            return Double.valueOf(value);
        } else {
            return value;
        }
    }

    /**
     * 获取参数信息
     * @param controllerMethod
     */
    private void getMethodParameterInfo(Method controllerMethod) {
        Parameter[] parameters = controllerMethod.getParameters();
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                Class<?> type = parameter.getType();
                
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    RequestParam annotation = parameter.getAnnotation(RequestParam.class);

                    String paramName = annotation.name();
                    if (paramName != null) {
                        name = paramName;
                    }

                    boolean required = annotation.required();
                    if (required) {
                        requiredParameterMap.put(name, type);
                    } else {
                        unRequiredParameterMap.put(name, type);
                    }
                } else {
                    unRequiredParameterMap.put(name, type);
                }

                parameterNames.add(name);
            }
        }
    }

    public HandlerAdapter(HandlerMapping handlerMapping, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.handlerMapping = handlerMapping;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public HandlerMapping getHandlerMapping() {
        return handlerMapping;
    }

    public void setHandlerMapping(HandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
}
