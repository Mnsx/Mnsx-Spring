package top.mnsx.spring_mnsx.mvc.processor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:35
 * @Description: JSP处理器
 */
public class JspResourceProcess implements ProcessorChain {
    @Override
    public boolean doProcess(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith(".jsp")) {
            RequestDispatcher jspDispatcher = request.getServletContext().getNamedDispatcher("jsp");
            try {
                jspDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
        return true;
    }
}
