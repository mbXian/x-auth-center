package com.xmb.auth.controller;

import com.xmb.auth.ShiroApiService;
import com.xmb.auth.auth.dto.GetUserByTokenDTO;
import com.xmb.auth.entity.SysUserEntity;
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
    private ShiroApiService shiroApiService;

    protected SysUserEntity getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {

            throw AuthException.PARAM_ERROR;
        }

        GetUserByTokenDTO getUserByTokenDTO = new GetUserByTokenDTO();
        getUserByTokenDTO.setToken(token);

        SysUserEntity sysUserEntity = shiroApiService.getUserByToken(getUserByTokenDTO);
        if (sysUserEntity == null) {

            throw AuthException.TOKEN_OUT_TIME;
        }

        return sysUserEntity;
    }

    protected Long getUserId() {
        return this.getUser().getId();
    }
}
