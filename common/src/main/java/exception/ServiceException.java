package exception;

import common.BaseEnumInterface;

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
     * @param exceptionInfo 异常枚举
     */
    public ServiceException(BaseEnumInterface exceptionInfo) {
        super(exceptionInfo.getMessage());
        this.code = exceptionInfo.getCode();
    }

    /**
     * 构造器
     *
     * @param code 异常编码
     * @param message 异常提示信息
     */
    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

}

