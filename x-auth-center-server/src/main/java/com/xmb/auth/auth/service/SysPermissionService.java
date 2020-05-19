package com.xmb.auth.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmb.auth.entity.SysPermissionEntity;

import java.util.List;

/**
 * 权限表
 *
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
public interface SysPermissionService extends IService<SysPermissionEntity> {

    /**
     * 查询角色的所有权限
     * @param roleId
     * @return
     */
    List<SysPermissionEntity> selectSysPermissionByRoleId(Long roleId);
}

