package top.mnsx.spring_mnsx.container.exception;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 23:26
 * @Description: 过多的bean被匹配
 */
public class ToMuchBeanExistException extends RuntimeException {
    public ToMuchBeanExistException() {
        super();
    }

    public ToMuchBeanExistException(String message) {
        super(message);
    }

    public ToMuchBeanExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToMuchBeanExistException(Throwable cause) {
        super(cause);
    }

    protected ToMuchBeanExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
