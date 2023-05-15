package top.mnsx.spring_mnsx.mvc.entity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 16:21
 * @Description:
 */
public class ErrorHandler {
    public static void handle(HttpServletResponse response, Object data, String msg) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器内部错误: " + (data != null ? data : "") + (msg != null ? msg : ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
