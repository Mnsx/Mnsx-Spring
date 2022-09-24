package top.mnsx.my_spring.exception;

/**
 * @BelongsProject: my_sprint
 * @BelongsPackage: top.mnsx.my_spring.exception
 * @CreateTime: 2022/8/28
 * @Description:
 */
public class NoReturnMatchException extends Exception {
    public NoReturnMatchException() {
        super();
    }

    public NoReturnMatchException(String message) {
        super(message);
    }

    public NoReturnMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoReturnMatchException(Throwable cause) {
        super(cause);
    }

    protected NoReturnMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
