package top.mnsx.spring_mnsx.container.exception;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 23:26
 * @Description: bean不符合
 */
public class BeanNotMatchException extends RuntimeException {
    public BeanNotMatchException() {
        super();
    }

    public BeanNotMatchException(String message) {
        super(message);
    }

    public BeanNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanNotMatchException(Throwable cause) {
        super(cause);
    }

    protected BeanNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
