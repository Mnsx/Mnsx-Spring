package top.mnsx.my_spring.mvc.processor;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProcessorChain {
    boolean doProcess(HttpServletRequest req, HttpServletResponse resp);
}
