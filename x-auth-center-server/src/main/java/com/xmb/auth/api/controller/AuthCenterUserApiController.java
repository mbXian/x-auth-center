package com.xmb.auth.api.controller;

import com.xmb.auth.AuthCenterUserApiService;
import com.xmb.auth.auth.dto.CheckoutPasswordDTO;
import com.xmb.auth.auth.dto.UserInfoSaveOrUpdateDTO;
import com.xmb.auth.auth.service.SysUserService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("api/auth/sysuser")
public class AuthCenterUserApiController implements AuthCenterUserApiService {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("验证手机号和密码是否匹配")
    @Override
    public boolean checkUserMobilePassword(CheckoutPasswordDTO checkoutPasswordDTO) {

        return sysUserService.checkUserMobilePassword(checkoutPasswordDTO.getMobile(), checkoutPasswordDTO.getPassword());
    }

    @ApiOperation("新增或修改用户信息")
    @RequestMapping("/saveOrUpdateUserInfo")
    @Override
    public void saveOrUpdateUserInfo(@RequestBody UserInfoSaveOrUpdateDTO userInfoSaveOrUpdateDTO) {
        sysUserService.saveOrUpdateUserInfo(userInfoSaveOrUpdateDTO);
    }

}
