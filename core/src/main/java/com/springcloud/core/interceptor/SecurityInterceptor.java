package com.springcloud.core.interceptor;

import com.springcloud.core.constant.Constant;
import exception.ServiceException;
import common.SysBaseEnumEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 接口拦截器
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        // 如果是OPTIONS结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }
        String token = request.getHeader(Constant.AUTH_TOKEN_KEY);
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String jumpUrl = requestUri.substring(contextPath.length());
        // 静态文件暂不做拦截
        String[] content = jumpUrl.split("\\.");
        String [] fileTypes = {"jpg", "png", "jpeg", "gif", "ico", "txt"};
        if (content.length == 2
                && jumpUrl.endsWith(content[1])
                && Arrays.asList(fileTypes).contains(content[1].toLowerCase())) {
            return true;
        }
        if (StringUtils.hasText(token) && redisUtil.getExpire(token) > 0 &&  redisUtil.hasKey(token)) {
            // token 有效
            redisUtil.expire(token,Constant.REDIS_LOGIN_EXPIRE);
        } else {
            log.info("jumpUrl = {}", jumpUrl);
            // token 为空，鉴权失败
            throw new ServiceException(SysBaseEnumEnum.TOKEN_IS_EMPTY);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
