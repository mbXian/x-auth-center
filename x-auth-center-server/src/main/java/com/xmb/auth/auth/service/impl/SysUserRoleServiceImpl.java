package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysUserRoleDao;
import com.xmb.auth.auth.entity.SysUserRoleEntity;
import com.xmb.auth.auth.service.SysUserRoleService;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {



}