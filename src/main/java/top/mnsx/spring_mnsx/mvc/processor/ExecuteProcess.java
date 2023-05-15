package top.mnsx.spring_mnsx.mvc.processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/8 15:14
 * @Description: 责任链执行
 */
public class ExecuteProcess {
    private List<ProcessorChain> processorChains;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public void execute() {
        if (processorChains != null) {
            for (ProcessorChain processorChain : processorChains) {
                // 执行责任链
                boolean flag = processorChain.doProcess(request, response);

                // 如果不允许通过那么则跳出，也就是能够被处理
                if (!flag) {
                    break;
                }
            }
        }
    }

    public ExecuteProcess(List<ProcessorChain> processorChains, HttpServletRequest request, HttpServletResponse response) {
        this.processorChains = processorChains;
        this.request = request;
        this.response = response;
    }

    public List<ProcessorChain> getProcessorChains() {
        return processorChains;
    }

    public void setProcessorChains(List<ProcessorChain> processorChains) {
        this.processorChains = processorChains;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
