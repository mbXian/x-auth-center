package com.xmb.auth.auth.dao;

import com.xmb.auth.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表
 * 
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    List<SysRoleEntity> selectSysRoleByUserId(@Param("userId") Long userId);
}
