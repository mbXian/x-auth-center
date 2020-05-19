package com.xmb.auth.api.controller;

import com.xmb.auth.ShiroApiService;
import com.xmb.auth.auth.dto.GetUserByTokenDTO;
import com.xmb.auth.auth.service.SysUserTokenService;
import com.xmb.auth.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@RestController
@RequestMapping("api/auth/shiro")
public class ShiroApiController implements ShiroApiService {

    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 根据token查询用户
     * @param getUserByTokenDTO
     * @return
     */
    @Override
    public SysUserEntity getUserByToken(@RequestBody GetUserByTokenDTO getUserByTokenDTO) {

        return sysUserTokenService.getUserByToken(getUserByTokenDTO.getToken());
    }
}
