package com.xmb.auth.oauth2;

import com.alibaba.fastjson.JSON;
import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.auth.entity.SysPermissionEntity;
import com.xmb.auth.auth.entity.SysRoleEntity;
import com.xmb.auth.auth.service.ShiroService;
import com.xmb.auth.auth.entity.SysUserEntity;
import com.xmb.auth.auth.service.SysPermissionService;
import com.xmb.auth.auth.service.SysRoleService;
import com.xmb.auth.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 授权
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权doGetAuthorizationInfo params = " + JSON.toJSONString(principals));
        //获取用户ID
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUserEntity sysUserEntity = (SysUserEntity) principals.getPrimaryPrincipal();
        Long userId = sysUserEntity.getId();
        //这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<String>();
        Set<String> permsSet = new HashSet<String>();
        //查询角色和权限(这里根据业务自行查询)
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.selectSysRoleByUserId(userId);
        for (SysRoleEntity sysRoleEntity : sysRoleEntityList) {

            rolesSet.add(sysRoleEntity.getRoleName());

            List<SysPermissionEntity> sysPermissionEntityList = sysPermissionService.selectSysPermissionByRoleId(sysRoleEntity.getId());
            for (SysPermissionEntity sysPermissionEntity : sysPermissionEntityList) {
                permsSet.add(sysPermissionEntity.getPermission());
            }
        }
        //将查到的权限和角色分别传入authorizationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }

    /**
     * 认证登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("认证登录doGetAuthenticationInfo params = {}", JSON.toJSONString(authenticationToken));
        String userName = (String) authenticationToken.getPrincipal();
//        //根据accessToken，查询用户信息
//        SysUserTokenDto tokenEntity = shiroService.queryByToken(accessToken);
//        //token失效
//        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw AuthException.TOKEN_OUT_TIME;
//        }
        //查询用户信息
        SysUserEntity userEntity = shiroService.selectUserByName(userName);
        log.info("user = {}", JSON.toJSONString(userEntity));
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userEntity, // 用户名
                userEntity.getPassword(), // 密码
                ByteSource.Util.bytes(userEntity.getSalt()), // salt=username+salt
                getName() // realm name
        );
        return simpleAuthenticationInfo;
    }
}
