/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xmb.auth.auth.service;


import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.auth.entity.SysUserEntity;

/**
 * 用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService {

	/**
	 * 生成token
	 * @param user  用户ID
	 */
	SysUserTokenDto createToken(SysUserEntity user);

	/**
	 * 保存用户token
	 * @param sysUserEntity
	 * @param token
	 */
	void saveUserToken(SysUserEntity sysUserEntity, String token);

	/**
	 * 根据token查询用户
	 * @return
	 */
	SysUserEntity getUserByToken(String token);

	/**
	 * 删除用户token
	 * @param sysUserEntity
	 */
	void deleteUserToken(SysUserEntity sysUserEntity);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);
}
