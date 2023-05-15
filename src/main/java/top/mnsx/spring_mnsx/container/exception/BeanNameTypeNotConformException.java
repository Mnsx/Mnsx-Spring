package top.mnsx.spring_mnsx.container.exception;

/**
 * @BelongsProject: spring-mnsx
 * @User: Mnsx_x
 * @CreateTime: 2022/10/5 23:27
 * @Description: 名字、类型不匹配
 */
public class BeanNameTypeNotConformException extends RuntimeException {
    public BeanNameTypeNotConformException() {
        super();
    }

    public BeanNameTypeNotConformException(String message) {
        super(message);
    }

    public BeanNameTypeNotConformException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanNameTypeNotConformException(Throwable cause) {
        super(cause);
    }

    protected BeanNameTypeNotConformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
