package com.xmb.auth.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmb.auth.auth.dto.SysUserLoginDto;
import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.entity.SysUserEntity;

/**
 * 用户表
 *
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 验证手机号和密码是否匹配
     * @param mobile
     * @param password
     * @return
     */
    boolean checkUserMobilePassword(String mobile, String password);

    /**
     * 用户登录
     * @param sysUserLoginDto
     * @return
     */
    SysUserTokenDto login(SysUserLoginDto sysUserLoginDto);

    /**
     * 登出
     */
    void logout();

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    SysUserEntity queryByMobile(String mobile);

    /**
     * 根据用户名查询实体
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUserEntity selectUserByName(String username);
}

