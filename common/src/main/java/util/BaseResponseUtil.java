package util;

import model.BaseResponse;
import common.Constant;
import common.BaseEnumInterface;
import exception.ServiceException;
import common.SysExceptionEnum;

/**
 * 通用返回数据模型
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
public final class BaseResponseUtil {

    /**
     * 成功编码
     */
    private static final Integer SUCCESS_CODE = 200;

    /**
     * 默认提示 操作成功
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    /**
     * 构造器私有，防止外部实例化
     */
    private BaseResponseUtil() {
        // TODO 可抛出异常，防止通过反射实例化对象
        throw new ServiceException(SysExceptionEnum.SYS_EXCEPTION);
    }

    /**
     * 适合 写操作（没有数据实体场景）调用
     *
     * @return 响应实体
     */
    public static BaseResponse<Object> success() {
        return new BaseResponse<>().setCode(SUCCESS_CODE).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 适合 查询操作（有数据实体场景）调用
     *
     * @param data 数据实体
     * @return 响应实体
     */
    public static BaseResponse<Object> success(Object data) {
        return success().setData(data);
    }

    /**
     * 适合 增、删、改操作（有数据实体场景）调用
     *
     * @param operationEnum 枚举
     * @return 响应实体
     */
    public static BaseResponse<Object> success(BaseEnumInterface operationEnum) {
        return success().setCode(operationEnum.getCode()).setMessage(operationEnum.getMessage());
    }

    /**
     * 校验框架异常场景 调用
     *
     * @param message 异常提示信息
     * @return 响应实体
     */
    public static BaseResponse<Object> fail(Integer code, String message) {
        return new BaseResponse<>().setStatus(Constant.FAIL).setCode(code).setMessage(message);
    }


    /**
     * 自定义业务异常场景 调用
     *
     * @param baseResponseCode 异常枚举
     * @return 响应实体
     */
    public static BaseResponse<Object> fail(BaseEnumInterface baseResponseCode) {
        return new BaseResponse<>().setStatus(Constant.FAIL).setCode(baseResponseCode.getCode()).setMessage(baseResponseCode.getMessage());
    }

    /**
     * 系统异常场景 调用
     *
     * @param sysExceptionEnum 异常枚举
     * @return 响应实体
     */
    public static BaseResponse<Object> error(BaseEnumInterface sysExceptionEnum) {
        return new BaseResponse<>().setStatus(Constant.ERROR).setCode(sysExceptionEnum.getCode()).setMessage(sysExceptionEnum.getMessage());
    }
}

