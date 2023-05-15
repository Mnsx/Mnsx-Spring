package top.mnsx.spring_mnsx.container.exception;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 23:52
 * @Description: Bean已经存在
 */
public class BeanHasExistException extends RuntimeException {
    public BeanHasExistException() {
        super();
    }

    public BeanHasExistException(String message) {
        super(message);
    }

    public BeanHasExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanHasExistException(Throwable cause) {
        super(cause);
    }

    protected BeanHasExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
