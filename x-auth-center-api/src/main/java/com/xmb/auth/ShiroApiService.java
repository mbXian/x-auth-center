package com.xmb.auth;

import com.xmb.auth.auth.dto.GetUserByTokenDTO;
import com.xmb.auth.entity.SysUserEntity;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@FeignClient(value = "x-auth-center",contextId = "shiroApi", path = "api/auth/shiro")
@Api(description = "Shiro模块")
public interface ShiroApiService {

    /**
     * 根据token查询用户
     * @param getUserByTokenDTO
     * @return
     */
    @RequestMapping("/getUserByToken")
    SysUserEntity getUserByToken(@RequestBody GetUserByTokenDTO getUserByTokenDTO);
}
