package com.xmb.auth.auth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * 
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 密码（MD5）
	 */
	private String password;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 邮箱
	 */
	private String email;
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
