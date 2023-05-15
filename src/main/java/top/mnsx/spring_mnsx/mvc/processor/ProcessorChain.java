package top.mnsx.spring_mnsx.mvc.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:03
 * @Description: processor责任链
 */
public interface ProcessorChain {
    boolean doProcess(HttpServletRequest request, HttpServletResponse response);
}
