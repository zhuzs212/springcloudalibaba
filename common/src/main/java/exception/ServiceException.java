package exception;

/**
 * 业务异常类
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public class ServiceException extends RuntimeException {

    /**
     * 异常码
     */
    public final int code;

    /**
     * 构造器
     *
     * @param exceptionInfo
     */
    public ServiceException(ExceptionInterface exceptionInfo) {
        super(exceptionInfo.getMessage());
        this.code = exceptionInfo.getCode();
    }

    /**
     * 构造器
     *
     * @param code
     * @param message
     */
    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

}

