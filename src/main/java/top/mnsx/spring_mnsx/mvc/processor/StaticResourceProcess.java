package top.mnsx.spring_mnsx.mvc.processor;

import top.mnsx.spring_mnsx.mvc.parser.XmlSpringMvcConfigParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:20
 * @Description: 静态资源处理器
 */
public class StaticResourceProcess implements ProcessorChain {
    private final List<String> staticResource;

    public StaticResourceProcess(String springMvcConfigLocation) {
        staticResource = XmlSpringMvcConfigParser.getStaticResources(springMvcConfigLocation);
    }

    @Override
    public boolean doProcess(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        if (staticResource != null) {
            for (String staticResource : staticResource) {
                // 如果静态资源被配置文件声明了处理那么就直接交给tomcat处理
                if (requestURI.contains(staticResource)) {
                    RequestDispatcher dispatcher = request.getServletContext().getNamedDispatcher("default");
                    response.setStatus(HttpServletResponse.SC_OK);

                    try {
                        dispatcher.forward(request, response);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }

                    return false;
                }
            }
        }

        return true;
    }
}
