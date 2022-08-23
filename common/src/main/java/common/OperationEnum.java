package common;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 全局枚举常量类
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@AllArgsConstructor
@Accessors(chain = true)
public enum OperationEnum implements ExceptionInterface {
    /**
     * 登陆成功
     */
    LOGIN_SUCCESS(200, "登陆成功！"),
    /**
     * 新增操作成功
     */
    SAVE_SUCCESS(201, "新增成功！"),
    /**
     * 修改操作成功
     */
    UPDATE_SUCCESS(202, "修改成功！"),
    /**
     * 删除操作成功
     */
    DELETE_SUCCESS(203, "删除成功！"),

    /**
     * 操作异常
     */
    OPERATION_ERROR(204, "操作失败！");
    /**
     * 结果类型CODE
     */
    private final Integer code;

    /**
     * 结果类型描述
     */
    private final String message;

    /**
     * 结果类型描述
     */
    @Override
    public int getCode() {
        return this.code;
    };

    /**
     * 获取异常信息
     */
    @Override
    public String getMessage() {
        return this.message;
    };
}
