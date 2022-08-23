package common;

/**
 * 枚举类 封装
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public interface ExceptionInterface {
    /**
     * 获取错误码
     *
     * @return
     */
    int getCode();

    /**
     * 获取异常信息
     *
     * @return
     */
    String getMessage();
}
