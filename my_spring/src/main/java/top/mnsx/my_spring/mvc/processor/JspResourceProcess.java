package top.mnsx.my_spring.mvc.processor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.processor
 * @CreateTime: 2022/8/2
 * @Description:
 */
public class JspResourceProcess implements ProcessorChain {

    @Override
    public boolean doProcess(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        if (requestURI.endsWith(".jsp")) {
            RequestDispatcher jsp = req.getServletContext().getNamedDispatcher("jsp");
            try {
                jsp.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
