package top.mnsx.spring_mnsx.mvc.processor;

import top.mnsx.spring_mnsx.annotation.mvc.RequestMapping;
import top.mnsx.spring_mnsx.container.handler.HandlerAdapter;
import top.mnsx.spring_mnsx.mvc.entity.ErrorHandler;
import top.mnsx.spring_mnsx.mvc.entity.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:43
 * @Description:
 */
public class ControllerProcess implements ProcessorChain {
    Map<Class<?>, Object> controllerMap;
    Map<String, HandlerMapping> handlerMappings = new HashMap<>();

    public ControllerProcess(Map<Class<?>, Object> controllerMap) {
        this.controllerMap = controllerMap;
    }

    @Override
    public boolean doProcess(HttpServletRequest request, HttpServletResponse response) {
        // 解析controllerMap
        if (controllerMap.size() > 0) {
            Set<Map.Entry<Class<?>, Object>> entries = controllerMap.entrySet();
            for (Map.Entry<Class<?>, Object> entry : entries) {
                Class<?> controllerClass = entry.getKey();
                Object controller = entry.getValue();

                // 遍历其中的方法
                Method[] declaredMethods = controllerClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    StringBuilder stringBuilder = new StringBuilder();

                    // 判断是否为controller
                    boolean requestMappingAnnotation = controllerClass.isAnnotationPresent(RequestMapping.class);
                    if (requestMappingAnnotation) {
                        RequestMapping annotation = controllerClass.getAnnotation(RequestMapping.class);
                        // 获取注解的值
                        String value = annotation.value();
                        // 解析注解值
                        value = value.startsWith("/") ? value : "/" + value;
                        stringBuilder.append(value);
                    }

                    HandlerMapping handlerMapping = new HandlerMapping(controller, declaredMethod);
                    handlerMappings.put(stringBuilder.toString(), handlerMapping);
                }
            }
        }

        String requestURI = request.getRequestURI();
        HandlerMapping handlerMapping = handlerMappings.get(requestURI);

        if (handlerMapping != null) {
            try {
                HandlerAdapter handlerAdapter = new HandlerAdapter(handlerMapping, request, response);

                handlerAdapter.handle();
            } catch (Exception e) {
                ErrorHandler.handle(response, null, null);
            }
        } else {
            ErrorHandler.handle(response, null, "没有找到对应的控制器");
        }

        return false;
    }
}
