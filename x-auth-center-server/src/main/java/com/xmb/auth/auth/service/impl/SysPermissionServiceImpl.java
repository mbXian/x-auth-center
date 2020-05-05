package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysPermissionDao;
import com.xmb.auth.auth.entity.SysPermissionEntity;
import com.xmb.auth.auth.service.SysPermissionService;


@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermissionEntity> implements SysPermissionService {

}