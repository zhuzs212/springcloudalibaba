package exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统异常 枚举
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@AllArgsConstructor
public enum SysExceptionEnum implements ExceptionInterface {
    /* ===========================User====================================== */
    /**
     * 数据校验异常
     */
    PARAMETER_EMPTY_EXCEPTION(10000, null),

    /**
     * 用户名不存在
     */
    ACCOUNT_IS_EMPTY(10001, "用户名不能为空!"),

    /**
     * 用户名不存在
     */
    ACCOUNT_NOT_EXIST(10002, "用户不存在!"),

    /**
     * 用户名或密码错误
     */
    ACCOUNT_OR_PASSWORD_ERROR(10003, "用户名或密码错误，请重新输入!"),

    /**
     * token为空，鉴权失败
     */
    TOKEN_IS_EMPTY(10004, "token 为空，鉴权失败!"),

    /**
     * token已失效
     */
    TOKEN_EXPIRED_EXCEPTION(10005, "token 已失效!"),

    /**
     * token 验证异常
     */
    JWT_VERIFICATION_EXCEPTION(10006, "token 验证异常!"),

    /**
     * 无权访问
     */
    PERMISSION_NOT(10007, "无访问权限"),

    /**
     * 系统发生异常
     */
    SYS_EXCEPTION(10008, "系统发生异常!"),

    /**
     * 创建线程失败异常
     */
    CREATE_THREAD_EXCEPTION(10009, "创建线程失败!"),

    /**
     * 创建线程失败异常
     */
    INCREMENT_LESS_THAN_ZERO(10010, "递增因子小于0!"),

    /**
     * 文件导出关闭流异常
     */
    EXPORT_EXCEPTION(10011, "文件导出关闭流异常!"),


    /**
     * 账号已被禁用
     */
    ACCOUNT_IS_DISABLE(10012, "您的账号已被禁用，请联系管理员"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(10013, "用户名或密码错误，请重新输入!"),

    /**
     * 文件读取失败
     */
    FILE_READ_EXCEPTION(10014, "文件读取异常!"),
    ;

    @Getter
    private final int code;

    @Getter
    private final String message;

}
