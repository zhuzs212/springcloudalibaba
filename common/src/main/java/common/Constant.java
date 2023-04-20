package common;

import exception.ServiceException;

/**
 * 常量类
 *
 * @author zhu_zishuang
 * @date 2020-04-22 14:22
 */
public final class Constant {
    /**
     * 构造器私有化
     */
    private Constant() {
        // TODO 可抛出异常，防止通过反射实例化对象
        throw new ServiceException(SysExceptionEnum.SYS_EXCEPTION);
    }

    /**
     * 返回结果 success：成功，fail：业务返回的失败，error：非业务异常失败
     */
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";

    /**
     * 常用数值
     */
    public static final Integer ONE = 1;
    public static final Long MAX_PAGE_SIZE = 100_000L;


    /**
     * 是否
     */
    public static final Byte YES = 1;
    public static final Byte NO = 0;
}
