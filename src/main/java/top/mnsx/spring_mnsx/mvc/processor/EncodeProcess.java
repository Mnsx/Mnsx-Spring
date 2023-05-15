package top.mnsx.spring_mnsx.mvc.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:19
 * @Description: 字符集处理器
 */
public class EncodeProcess implements ProcessorChain {
    @Override
    public boolean doProcess(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
