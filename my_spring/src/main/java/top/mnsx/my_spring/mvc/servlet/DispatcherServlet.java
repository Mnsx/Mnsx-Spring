package top.mnsx.my_spring.mvc.servlet;

import org.apache.ibatis.javassist.ClassPath;
import top.mnsx.my_spring.container.ClassPathXmlApplicationContext;
import top.mnsx.my_spring.mvc.processor.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc
 * @CreateTime: 2022/7/24
 * @Description:
 */
public class DispatcherServlet extends HttpServlet {

    private List<ProcessorChain> processorChains = new ArrayList<>();

    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        String contextConfigLocation = this.getServletConfig().getInitParameter("contextConfigLocation");
        String springMvc = contextConfigLocation.split(":")[1];

        ClassPathXmlApplicationContext context = ClassPathXmlApplicationContext.getContext();
        Map<Class<?>, Object> controllerMap = context.getControllerMap();

        processorChains.add(new EncodeProcess());
        processorChains.add(new StaticResourceProcess(springMvc));
        processorChains.add(new JspResourceProcess());
        processorChains.add(new ControllerProcess(controllerMap));

    }

    /**
     * 没执行一次请求，调用一次service方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用户请求的路径
        String requestURI = req.getRequestURI();

        ExecuteProcess process = new ExecuteProcess(processorChains, req, resp);
        process.execute();
    }
}
