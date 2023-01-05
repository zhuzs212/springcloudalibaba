package common;

/**
 * @author zhuzishuang
 * @date 2022/5/17
 */
public enum RedisErrorEnum implements BaseEnumInterface {

    /**
     * 获取redis连接池错误
     */
    REDIS_CONN_EXCEPTION(9000, "获取redis连接池错误"),
    /**
     * redis参数错误
     */
    REDIS_PARAM_EXCEPTION(9001, "redis参数错误");

    private Integer code = null;
    private String message = null;

    RedisErrorEnum(Integer errCode, String errMessage) {
        this.code = errCode;
        this.message = errMessage;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
