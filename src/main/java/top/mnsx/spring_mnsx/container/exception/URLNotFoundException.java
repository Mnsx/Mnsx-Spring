package top.mnsx.spring_mnsx.container.exception;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 16:58
 * @Description: 路径不存在异常
 */
public class URLNotFoundException extends RuntimeException {
    public URLNotFoundException() {
        super();
    }

    public URLNotFoundException(String message) {
        super(message);
    }

    public URLNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLNotFoundException(Throwable cause) {
        super(cause);
    }

    protected URLNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
