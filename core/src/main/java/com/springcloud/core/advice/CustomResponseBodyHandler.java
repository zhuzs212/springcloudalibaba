package com.springcloud.core.advice;

import common.OperationEnum;
import exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import util.BaseResponseUtil;

import java.text.MessageFormat;

/**
 * 自定义全局返回值包装处理类
 *
 * @author zhu_zishuang
 * @date 2021-03-12
 */
@Slf4j
@ControllerAdvice(basePackages = "com.zhuzs.admin.controller")
public class CustomResponseBodyHandler<T> implements ResponseBodyAdvice<T> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        /*
         * 增、删、改操作返回的状态
         */
        if (null == body) {
            throw new ServiceException(OperationEnum.OPERATION_ERROR);
        }
        if (OperationEnum.class.equals(body.getClass())) {
            log.info(MessageFormat.format("接口: '{}' , 增、删、改方法！", request.getURI()));
            return (T) BaseResponseUtil.success((OperationEnum) body);
        }

        /*
         * 读操作
         */
        return (T) BaseResponseUtil.success(body);
    }
}
