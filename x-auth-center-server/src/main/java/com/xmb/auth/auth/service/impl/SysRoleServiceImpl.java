package com.xmb.auth.auth.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmb.auth.auth.dao.SysRoleDao;
import com.xmb.auth.auth.entity.SysRoleEntity;
import com.xmb.auth.auth.service.SysRoleService;

import java.util.List;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {

        return this.baseMapper.selectSysRoleByUserId(userId);
    }
}