package com.xmb.auth;

import com.xmb.auth.auth.dto.SysUserTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@FeignClient(value = "x-auth-center",contextId = "shiroApi", path = "api/shiro")
public interface ShiroApiService {

    /**
     * 从Redis中获取用户信息
     * @param token
     * @return
     */
    @RequestMapping("queryByToken")
    SysUserTokenDto queryByToken(@RequestParam("token") String token);
}
