package com.xmb.auth.api.controller;

import com.xmb.auth.ShiroApiService;
import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.auth.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@RequestMapping("api/shiro")
public class ShiroApiController implements ShiroApiService {

    @Autowired
    private ShiroService shiroService;

    /**
     * 从Redis中获取用户信息
     * @param token
     * @return
     */
    @Override
    public SysUserTokenDto queryByToken(String token) {

        SysUserTokenDto sysUserTokenDto = shiroService.queryByToken(token);
//        if (sysUserTokenDto != null) {
//            SysUserEntity sysUserEntity = shiroService.queryUser(sysUserTokenDto.getUserId());
////            sysUserEntity.setPrems(shiroService.getUserPermissions(sysUserEntity.getId()));
//            sysUserTokenDto.setSysUserEntity(sysUserEntity);
//        }
        return sysUserTokenDto;
    }
}
