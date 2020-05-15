/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.xmb.auth.oauth2;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xmb.common.network.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2过滤器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {

        log.info("OAuth2Filter createToken...");
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        
        log.info("OAuth2Filter isAccessAllowed mappedValue = {}", JSON.toJSONString(mappedValue));
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        log.info("OAuth2Filter onAccessDenied...");
        String url = ((HttpServletRequest) request).getRequestURI();
        if (url.startsWith("/api/")) {
            return true;
        }
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            setHeader(httpResponse);
            String json = new Gson().toJson(Result.fail(HttpStatus.UNAUTHORIZED.value(), "无token，请登陆"));
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        log.info("OAuth2Filter onLoginFailure token = {}, exception = {}", JSON.toJSONString(token), JSON.toJSONString(e));
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        setHeader(httpResponse);
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            String json = new Gson().toJson(Result.fail(HttpStatus.UNAUTHORIZED.value(), throwable.getMessage()));
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }
        return false;
    }

    private void setHeader(HttpServletResponse httpResponse) {
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
            Assert.notNull(httpServletRequest, "HttpServletRequest got from RequestContextHolder cannot be null. Please check the web config.");

            if (httpServletRequest == null) {

                httpResponse.setHeader("Access-Control-Allow-Origin", null);
            } else {

                httpResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            }

        } else {

        }
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }


}
