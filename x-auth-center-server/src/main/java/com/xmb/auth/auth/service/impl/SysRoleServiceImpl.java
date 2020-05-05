package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysRoleDao;
import com.xmb.auth.auth.entity.SysRoleEntity;
import com.xmb.auth.auth.service.SysRoleService;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {



}