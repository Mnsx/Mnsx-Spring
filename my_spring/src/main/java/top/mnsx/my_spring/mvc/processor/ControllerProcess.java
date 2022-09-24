package top.mnsx.my_spring.mvc.processor;

import top.mnsx.my_spring.mvc.annotation.RequestMapping;
import top.mnsx.my_spring.mvc.servlet.HandlerAdapter;
import top.mnsx.my_spring.mvc.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.processor
 * @CreateTime: 2022/8/2
 * @Description:
 */
public class ControllerProcess implements ProcessorChain {
    Map<Class<?>, Object> controllerMap;
    Map<String, HandlerMapping>  HandlerMappings = new HashMap<>();

    public ControllerProcess(Map<Class<?>, Object> controllerMap) {
        this.controllerMap = controllerMap;
    }

    @Override
    public boolean doProcess(HttpServletRequest req, HttpServletResponse resp) {
        if (controllerMap.size() > 0) {
            Set<Map.Entry<Class<?>, Object>> entries = controllerMap.entrySet();
            for (Map.Entry<Class<?>, Object> entry : entries) {
                Class<?> c = entry.getKey();
                Object o = entry.getValue();

                Method[] declaredMethods = c.getDeclaredMethods();
                if (declaredMethods != null) {
                    for (Method declaredMethod : declaredMethods) {
                        StringBuffer sb = new StringBuffer();

                        boolean annotationPresent = c.isAnnotationPresent(RequestMapping.class);
                        if (annotationPresent) {
                            RequestMapping requestMapping = c.getAnnotation(RequestMapping.class);
                            String value = requestMapping.value();
                            value = value.startsWith("/") ? value : "/" + value;
                            sb.append(value);
                        }

                        RequestMapping requestMapping = declaredMethod.getAnnotation(RequestMapping.class);
                        if (requestMapping != null) {
                            String value = requestMapping.value();
                            value = value.startsWith("/") ? value : "/" + value;
                            sb.append(value);
                        }

                        HandlerMapping handlerMapping = new HandlerMapping(o, declaredMethod);
                        HandlerMappings.put(sb.toString(), handlerMapping);
                    }
                }
            }
        }

        String requestURI = req.getRequestURI();
        HandlerMapping handlerMapping = HandlerMappings.get(requestURI);

        if (handlerMapping != null) {
            try {
                HandlerAdapter handlerAdapter = new HandlerAdapter(handlerMapping, req, resp);

                handlerAdapter.handle();
            } catch (Exception e) {
                resp.setContentType("text/html;charset=utf-8");
                try {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误！");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            try {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "没有找到对应的处理器!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
