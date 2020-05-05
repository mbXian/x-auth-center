package com.xmb.auth.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmb.auth.auth.entity.SysUserEntity;

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
}

