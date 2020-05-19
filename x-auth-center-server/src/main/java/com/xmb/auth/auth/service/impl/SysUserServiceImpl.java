package com.xmb.auth.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dto.SysUserLoginDto;
import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.entity.SysUserEntity;
import com.xmb.auth.auth.service.SysUserTokenService;
import com.xmb.auth.exception.AuthException;
import com.xmb.common.utils.MD5Utils;
import com.xmb.common.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xmb.auth.auth.dao.SysUserDao;
import com.xmb.auth.auth.service.SysUserService;
import org.springframework.util.StringUtils;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private RedisUtils redisUtils;

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

    /**
     * 用户登录
     * @param sysUserLoginDto
     * @return
     */
    @Override
    public SysUserTokenDto login(SysUserLoginDto sysUserLoginDto) {

        SysUserEntity sysUserEntity = selectUserByName(sysUserLoginDto.getUserName());
        if (sysUserEntity == null) {

            throw AuthException.SYSUSER_NOT_FOUND;
        }

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUserLoginDto.getUserName(), sysUserLoginDto.getPassword());

        try {
            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在UserRealm里认证方法设置的来验证
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            //账号不存在和下面密码错误一般都合并为一个账号或密码错误，这样可以增加暴力破解难度
            throw AuthException.SYSUSER_NOT_FOUND;
        } catch (DisabledAccountException e) {
            throw AuthException.ACCOUNT_FROZEN;
        } catch (IncorrectCredentialsException e) {
            throw AuthException.PASSWORD_ERROR;
        } catch (Throwable e) {
            throw AuthException.ERROR_UNKNOW;
        }

        String token = SecurityUtils.getSubject().getSession().getId().toString();

        //保存token
        sysUserTokenService.saveUserToken(sysUserEntity, token);

        SysUserTokenDto sysUserTokenDto = new SysUserTokenDto();
        sysUserTokenDto.setUserId(sysUserEntity.getId());
        sysUserTokenDto.setToken(token);
        return sysUserTokenDto;
    }

    /**
     * 登出
     */
    @Override
    public void logout() {

        String token = SecurityUtils.getSubject().getSession().getId().toString();
        SysUserEntity sysUserEntity = sysUserTokenService.getUserByToken(token);

        if (sysUserEntity != null) {

            sysUserTokenService.deleteUserToken(sysUserEntity);
        }

        SecurityUtils.getSubject().logout();
    }

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    @Override
    public SysUserEntity queryByMobile(String mobile) {

        SysUserEntity sysUserEntity = this.baseMapper.findByMobile(mobile);
        return sysUserEntity;
    }

    /**
     * 根据用户名查询实体
     * @param username
     * @return
     */
    @Override
    public SysUserEntity selectUserByName(String username) {

        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
        queryWrapper.eq("user_name", username);
        return getOne(queryWrapper);
    }
}