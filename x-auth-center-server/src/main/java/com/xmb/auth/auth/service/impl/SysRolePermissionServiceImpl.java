package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysRolePermissionDao;
import com.xmb.auth.entity.SysRolePermissionEntity;
import com.xmb.auth.auth.service.SysRolePermissionService;


@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionDao, SysRolePermissionEntity> implements SysRolePermissionService {



}