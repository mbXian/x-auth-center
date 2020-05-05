package com.xmb.auth.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.xmb.auth.network.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysPermissionDao;
import com.xmb.auth.auth.entity.SysPermissionEntity;
import com.xmb.auth.auth.service.SysPermissionService;


@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermissionEntity> implements SysPermissionService {

}