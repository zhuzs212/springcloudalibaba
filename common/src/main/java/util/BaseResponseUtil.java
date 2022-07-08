package util;

import model.BaseResponse;
import common.Constant;
import exception.ExceptionInterface;
import exception.ServiceException;
import exception.SysExceptionEnum;

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
     * 默认操作成功提示
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    /**
     * 构造器私有，防止外部实例化
     */
    private BaseResponseUtil() {
        // 防止反射
        throw new ServiceException(SysExceptionEnum.SYS_EXCEPTION);
    }

    /**
     * 包裹响应对象，此方法适合写操作没有数据实体场景下调用
     *
     * @return 响应实体
     */
    public static BaseResponse<Object> success() {
        return new BaseResponse<>().setCode(SUCCESS_CODE).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 包裹响应对象，此方法适合查询操作有数据实体场景下调用
     *
     * @param data 数据实体
     * @return 响应实体
     */
    public static BaseResponse<Object> success(Object data) {
        return success().setData(data);
    }

    /**
     * 包裹响应对象，此方法适合 增、删、改 操作有数据实体场景下调用
     *
     * @param operationEnum
     * @return
     */
    public static BaseResponse<Object> success(ExceptionInterface operationEnum) {
        return success().setCode(operationEnum.getCode()).setMessage(operationEnum.getMessage());
    }

    /**
     * 包裹响应对象，校验框架异常 场景下调用
     *
     * @param message 异常消息
     * @return 响应实体
     */
    public static BaseResponse<Object> fail(Integer code, String message) {
        return new BaseResponse<>().setStatus(Constant.FAIL).setCode(code).setMessage(message);
    }

    /**
     * 包裹响应对象，自定义业务异常 场景下调用
     *
     * @param baseResponseCode
     * @return
     */
    public static BaseResponse<Object> fail(ExceptionInterface baseResponseCode) {
        return new BaseResponse<>().setStatus(Constant.FAIL).setCode(baseResponseCode.getCode()).setMessage(baseResponseCode.getMessage());
    }

    /**
     * 包裹响应对象，系统异常 场景下调用
     *
     * @param sysExceptionEnum
     * @return 响应实体
     */
    public static BaseResponse<Object> error(ExceptionInterface sysExceptionEnum) {
        return new BaseResponse<>().setStatus(Constant.ERROR).setCode(sysExceptionEnum.getCode()).setMessage(sysExceptionEnum.getMessage());
    }
}

