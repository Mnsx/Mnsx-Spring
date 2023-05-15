package top.mnsx.spring_mnsx.container.handler;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.mnsx.spring_mnsx.annotation.mvc.CrossOrigin;
import top.mnsx.spring_mnsx.annotation.mvc.RequestParam;
import top.mnsx.spring_mnsx.annotation.mvc.ResponseBody;
import top.mnsx.spring_mnsx.mvc.entity.ErrorHandler;
import top.mnsx.spring_mnsx.mvc.entity.HandlerMapping;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 16:02
 * @Description: 处理handlerMapping
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerAdapter {
    // 处理器映射
    private HandlerMapping handlerMapping;
    // 请求
    private HttpServletRequest httpServletRequest;
    // 响应
    private HttpServletResponse httpServletResponse;
    // 需要的参数集合
    private final Map<String, Class<?>> requiredParameterMap = new ConcurrentHashMap<>();
    // 不是必须的参数集
    private final Map<String, Class<?>> unRequiredParameterMap = new ConcurrentHashMap<>();
    // 参数集合
    private final List<String> parameterNames = new ArrayList<>();

    public void handle() {
        Method controllerMethod = handlerMapping.getMethod();
        Object controllerObject = handlerMapping.getControlObject();

        // 获取方法的参数
        this.getMethodParameterInfo(controllerMethod);

        // 获取参数对象
        Object[] args = this.getArgs();

        Object result = null;
        try {
            result = controllerMethod.invoke(controllerObject, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
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

            // 返回json字符串
            this.responseJson(result);
        } else if (result instanceof String) {
            //跳转jsp视图
            this.responseJsp(result);
        } else {
            ErrorHandler.handle(httpServletResponse, null, "等待开发");
        }
    }

    /**
     * 获取方法的参数信息并且将其放入集合中
     * @param controllerMethod 控制器方法
     */
    private void getMethodParameterInfo(Method controllerMethod) {
        // 获取方法参数
        Parameter[] parameters = controllerMethod.getParameters();
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                // 获取方法的参数名称和类型
                String name = parameter.getName();
                Class<?> type = parameter.getType();

                // 判断参数是否被注解标记
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    RequestParam parameterAnnotation = parameter.getAnnotation(RequestParam.class);

                    // 获取参数名称
                    String paramName = parameterAnnotation.name();
                    if (paramName != null) {
                        name = paramName;
                    }

                    // 判断参数是否必要
                    boolean required = parameterAnnotation.required();
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

    /**
     * 获取参数对象
     * @return 返回参数对象数组
     */
    private Object[] getArgs() {
        Object[] args = new Object[parameterNames.size()];

        int index = 0;

        for (String parameterName : parameterNames) {
            // 根据名称获取参数
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            String[] values = parameterMap.get(parameterName);

            boolean ifRequired = requiredParameterMap.containsKey(parameterName);
            if (ifRequired) {
                Class<?> param = requiredParameterMap.get(parameterName);
                if (param == null) {
                    ErrorHandler.handle(httpServletResponse, parameterName, "参数缺失");
                } else {
                    args[index++] = this.convertValue(param, values);
                }
            } else {
                Class<?> param = unRequiredParameterMap.get(parameterName);
                if (values != null) {
                    args[index++] = this.convertValue(param,values);
                } else {
                    args[index++] = this.convertDefaultValue(param);
                }
            }
        }

        return args;
    }

    /**
     * 批量转换参数
     * @param c 参数类型
     * @param values 参数
     * @return 返回转换后的参数
     */
    private Object convertValue(Class<?> c, String[] values) {
        if (c.isArray()) {
            // 将参数转换为数组
            return values;
        } else {
            return this.convertSingleValue(c, values[0]);
        }
    }

    /**
     * 转换单个值
     * @param c 参数类型
     * @param value 参数值
     * @return 返回转换后对象
     */
    private Object convertSingleValue(Class<?> c, String value) {
        if (c == int.class || c == Integer.class) {
            return Integer.parseInt(value);
        } else if (c == double.class || c == Double.class) {
            return Double.parseDouble(value);
        } else if (c == long.class || c == Long.class) {
            return Long.parseLong(value);
        } else {
            return value;
        }
    }

    /**
     * 获取默认值参数
     * @param aClass 参数类型
     * @return 返回对象
     */
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
}
