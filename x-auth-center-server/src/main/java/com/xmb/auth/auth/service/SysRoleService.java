package com.xmb.auth.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmb.auth.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色表
 *
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
}

