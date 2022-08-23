package exception;

import common.ExceptionInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RedisException extends RuntimeException {

    private static final long serialVersionUID = -9161348021156852454L;

    private Integer errorCode;
    private String errorMessage;

    public RedisException(String message) {
    }

    public RedisException(ExceptionInterface e) {
        super(e.getMessage());
        this.errorCode = e.getCode();
        this.errorMessage = e.getMessage();
    }

    public RedisException(ExceptionInterface e, String msg) {
        super(msg);
        this.errorCode = e.getCode();
        this.errorMessage = msg;
    }

    public RedisException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.errorMessage = msg;
    }

    public RedisException(ExceptionInterface ie, Exception e) {
        super(ie.getMessage());
        this.errorCode = ie.getCode();
        this.errorMessage = e.getMessage();
    }
}
