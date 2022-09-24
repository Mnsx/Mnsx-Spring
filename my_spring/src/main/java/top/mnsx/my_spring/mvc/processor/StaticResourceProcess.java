package top.mnsx.my_spring.mvc.processor;

import top.mnsx.my_spring.parser.SpringMvcParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.processor
 * @CreateTime: 2022/8/2
 * @Description:
 */
public class StaticResourceProcess implements ProcessorChain{

    private String springMvcConfig;
    private List<String> staticResources;

    public StaticResourceProcess(String springMvcConfig) {
        this.springMvcConfig = springMvcConfig;
        staticResources = SpringMvcParser.getStaticResources(springMvcConfig);
    }

    @Override
    public boolean doProcess(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        if (staticResources != null) {
            for (String staticResource : staticResources) {
                if (requestURI.contains(staticResource)) {
                    //静态资源直接交给tomcat处理
                    RequestDispatcher dispatcher= req.getServletContext().getNamedDispatcher("default");
                    resp.setStatus(HttpServletResponse.SC_OK);
                    try {
                        dispatcher.forward(req, resp);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
