package com.xmb.auth;

import com.xmb.auth.auth.dto.SysUserTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
