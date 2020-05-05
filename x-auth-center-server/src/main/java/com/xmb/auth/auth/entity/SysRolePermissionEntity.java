package com.xmb.auth.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色权限表
 * 
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@TableId
	private Long roleId;
	/**
	 * 权限id
	 */
	private Long permissionId;
	/**
	 * 录入时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 删除状态（0：未删除；1：已删除）
	 */
	private Integer deleted;

}
