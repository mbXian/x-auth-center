/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xmb.auth.auth.service.impl;

import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.auth.service.ShiroService;
import com.xmb.auth.auth.service.SysUserRoleService;
import com.xmb.auth.auth.service.SysUserService;
import com.xmb.auth.entity.SysUserEntity;
import com.xmb.auth.exception.AuthException;
import com.xmb.auth.utils.RedisKeys;
import com.xmb.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取用户权限
     * 角色-菜单-权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissions(Long userId) {
//        List<String> permsList;
//        //系统管理员，只获取用户中心最高权限(废弃-拥有最高权限)
//        if(sysUserService.isSuperAdmin(userId)){
//            List<SysMenuEntity> menuList = menuService.queryListParentId(ApplicationConstant.AUTH_APP_ID,null,null);
//            permsList = new ArrayList<>(menuList.size());
//            for(SysMenuEntity menu : menuList){
//                permsList.add(menu.getPerms());
//            }
//        }else{
//            permsList = sysUserDao.queryAllPerms(userId);
//        }
        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
//        for(String perms : permsList){
//            if(StringUtils.isBlank(perms)){
//                continue;
//            }
//            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
//        }
        return permsSet;
    }

    /**
     * 从Redis中获取用户信息
     * @param token
     * @return
     */
    @Override
    public SysUserTokenDto queryByToken(String token) {

        //按照token获取用户id
        String userId = redisUtils.get(RedisKeys.AUTH_TOKEN + token);
        log.info("RequestToken:{} UserId:{}",token,userId);

        if(StringUtils.isBlank(userId)){
            throw AuthException.TOKEN_OUT_TIME;
        }
        return redisUtils.get(RedisKeys.AUTH_USER + userId, SysUserTokenDto.class);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        SysUserEntity userEntity = sysUserService.getById(userId);
        return userEntity;
    }

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    @Override
    public SysUserEntity queryByMobile(String mobile) {

        return sysUserService.queryByMobile(mobile);
    }

    /**
     * 根据用户名查询实体
     * @param username
     * @return
     */
    @Override
    public SysUserEntity selectUserByName(String username) {

        return sysUserService.selectUserByName(username);
    }
}
