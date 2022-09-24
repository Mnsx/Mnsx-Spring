package top.mnsx.my_spring.mvc.processor;

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
public class ExecuteProcess {
    //所有的处理器
    private List<ProcessorChain> processorChains;

    private HttpServletRequest request;

    private HttpServletResponse response;

    public void execute() {
        if (processorChains != null) {
            for (ProcessorChain processorChain : processorChains) {
                boolean b = processorChain.doProcess(request, response);
                if (!b) {
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
