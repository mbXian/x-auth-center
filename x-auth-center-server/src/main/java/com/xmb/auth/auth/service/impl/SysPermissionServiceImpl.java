package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysPermissionDao;
import com.xmb.auth.entity.SysPermissionEntity;
import com.xmb.auth.auth.service.SysPermissionService;

import java.util.List;


@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermissionEntity> implements SysPermissionService {

    /**
     * 查询角色的所有权限
     * @param roleId
     * @return
     */
    @Override
    public List<SysPermissionEntity> selectSysPermissionByRoleId(Long roleId) {

        return this.baseMapper.selectSysPermissionByRoleId(roleId);
    }
}