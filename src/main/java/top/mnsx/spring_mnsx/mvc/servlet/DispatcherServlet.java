package top.mnsx.spring_mnsx.mvc.servlet;

import top.mnsx.spring_mnsx.container.ClassPathXmlApplication;
import top.mnsx.spring_mnsx.mvc.processor.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:00
 * @Description: MVC启动类
 */
public class DispatcherServlet extends HttpServlet {
    // 责任链集合
    private final List<ProcessorChain> processorChains = new ArrayList<>();

    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        // 获取context
        ClassPathXmlApplication context = ClassPathXmlApplication.getContext();
        // 获取controllerMap集合
        Map<Class<?>, Object> controllerMap = context.getControllerMap();
        // 添加责任链
        processorChains.add(new EncodeProcess());
        processorChains.add(new StaticResourceProcess("springmvc.xml"));
        processorChains.add(new JspResourceProcess());
        processorChains.add(new ControllerProcess(controllerMap));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户请求路径
        String requestURI = req.getRequestURI();

        // 执行责任链，寻找处理器
        ExecuteProcess executeProcess = new ExecuteProcess(processorChains, req, resp);
        executeProcess.execute();
    }
}
