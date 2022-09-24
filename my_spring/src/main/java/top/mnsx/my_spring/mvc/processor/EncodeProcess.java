package top.mnsx.my_spring.mvc.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.mvc.processor
 * @CreateTime: 2022/8/2
 * @Description:
 */
public class EncodeProcess implements ProcessorChain{
    @Override
    public boolean doProcess(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {

        }

        return true;
    }
}
