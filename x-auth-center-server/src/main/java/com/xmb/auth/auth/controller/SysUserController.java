package com.xmb.auth.auth.controller;

import com.xmb.auth.auth.dto.CheckoutPasswordDTO;
import com.xmb.common.network.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    public Result<Boolean> checkUserMobilePassword(@RequestBody CheckoutPasswordDTO checkoutPasswordDTO) {

        return Result.ok(sysUserService.checkUserMobilePassword(checkoutPasswordDTO.getMobile(), checkoutPasswordDTO.getPassword()));
    }
}
