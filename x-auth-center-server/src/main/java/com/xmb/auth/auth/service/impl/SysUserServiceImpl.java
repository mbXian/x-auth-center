package com.xmb.auth.auth.service.impl;

import com.xmb.common.utils.MD5Utils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysUserDao;
import com.xmb.auth.auth.entity.SysUserEntity;
import com.xmb.auth.auth.service.SysUserService;
import org.springframework.util.StringUtils;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    /**
     * 验证手机号和密码是否匹配
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public boolean checkUserMobilePassword(String mobile, String password) {
        SysUserEntity sysUserEntity = this.baseMapper.findByMobile(mobile);
        String passwordMD5 = MD5Utils.encode(password);
        return (sysUserEntity != null && !StringUtils.isEmpty(sysUserEntity.getMobile()) && sysUserEntity.getPassword().equals(passwordMD5));
    }

}