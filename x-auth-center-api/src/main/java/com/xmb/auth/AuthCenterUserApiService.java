package com.xmb.auth;

import com.xmb.auth.auth.dto.CheckoutPasswordDTO;
import com.xmb.auth.auth.dto.UserInfoSaveOrUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ben
 * @date 2020-05-05
 * @desc
 */
@FeignClient(value = "x-auth-center",contextId = "sysuserApi" ,path = "api/auth/sysuser")
@Api(description = "用户中心模块")
public interface AuthCenterUserApiService {

    /**
     * 验证手机号和密码是否匹配
     * @param checkoutPasswordDTO
     * @return
     */
    @RequestMapping("/checkUserMobilePassword")
    boolean checkUserMobilePassword(@RequestBody CheckoutPasswordDTO checkoutPasswordDTO);

    /**
     * 新增或修改用户信息
     * @param userInfoSaveOrUpdateDTO
     */
    @RequestMapping("/saveOrUpdateUserInfo")
    void saveOrUpdateUserInfo(@RequestBody UserInfoSaveOrUpdateDTO userInfoSaveOrUpdateDTO);

}
