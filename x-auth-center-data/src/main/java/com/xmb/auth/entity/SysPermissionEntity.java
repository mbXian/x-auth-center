package com.xmb.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限表
 * 
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Data
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * parent id
	 */
	private Long parentId;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限类型
	 */
	private String type;
	/**
	 * 权限
	 */
	private String permission;
	/**
	 * URL
	 */
	private String url;
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
