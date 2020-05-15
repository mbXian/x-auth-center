package com.xmb.auth.auth.controller;

import com.xmb.auth.auth.dto.CheckoutPasswordDTO;
import com.xmb.auth.auth.dto.SysUserLoginDto;
import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.common.network.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import com.xmb.auth.auth.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户表
 *
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Slf4j
@Api(description = "用户模块")
@RestController
@RequestMapping("auth/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "验证手机号和密码是否匹配",notes = "验证手机号和密码是否匹配",consumes = "application/json")
    @PostMapping("/checkUserMobilePassword")
    @RequiresPermissions("checkUserMobilePassword")
    public Result<Boolean> checkUserMobilePassword(@RequestBody CheckoutPasswordDTO checkoutPasswordDTO) {

        return Result.ok(sysUserService.checkUserMobilePassword(checkoutPasswordDTO.getMobile(), checkoutPasswordDTO.getPassword()));
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",consumes = "application/json")
    @PostMapping("/login")
    public Result<SysUserTokenDto> login(@RequestBody SysUserLoginDto sysUserLoginDto) {

        return Result.ok(sysUserService.login(sysUserLoginDto));
    }

    @ApiOperation(value = "用户登出",notes = "用户登出",consumes = "application/json")
    @PostMapping("/logout")
    public Result logout() {

        sysUserService.logout();
        return Result.ok();
    }
}
