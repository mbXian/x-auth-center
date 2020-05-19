package com.xmb.auth.controller;

import com.xmb.auth.auth.entity.SysUserEntity;
import com.xmb.auth.auth.service.SysUserTokenService;
import com.xmb.auth.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ben
 * @date 2020-04-22
 * @desc
 */
public class BaseController {

    @Autowired
    private SysUserTokenService sysUserTokenService;

    protected SysUserEntity getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {

            throw AuthException.PARAM_ERROR;
        }

        SysUserEntity sysUserEntity = sysUserTokenService.getUserByToken(token);
        if (sysUserEntity == null) {

            throw AuthException.TOKEN_OUT_TIME;
        }

        return sysUserEntity;
    }

    protected Long getUserId() {
        return this.getUser().getId();
    }
}
