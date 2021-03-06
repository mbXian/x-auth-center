package com.xmb.auth.auth.dao;

import com.xmb.auth.entity.SysPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表
 * 
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Mapper
public interface SysPermissionDao extends BaseMapper<SysPermissionEntity> {
    /**
     * 查询角色的所有权限
     * @param roleId
     * @return
     */
    List<SysPermissionEntity> selectSysPermissionByRoleId(@Param("roleId") Long roleId);
}
