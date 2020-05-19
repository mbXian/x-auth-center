/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.xmb.auth.auth.service.impl;

import com.xmb.auth.auth.dto.SysUserTokenDto;
import com.xmb.auth.auth.service.SysUserTokenService;
import com.xmb.auth.entity.SysUserEntity;
import com.xmb.auth.exception.AuthException;
import com.xmb.auth.oauth2.TokenGenerator;
import com.xmb.auth.utils.RedisKeys;
import com.xmb.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Autowired
	private RedisUtils redisUtils;


	/**
	 * 创建token，登入时操作
	 * @param user  用户ID
	 * @return
	 */
	@Override
	public SysUserTokenDto createToken(SysUserEntity user) {
		//生成一个token
		String token = TokenGenerator.generateValue();
		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
		//从Redis中获取当前用户token信息
		SysUserTokenDto tokenDto = null;
		try {
			tokenDto = redisUtils.get(RedisKeys.AUTH_USER + user.getId(), SysUserTokenDto.class);
			if(tokenDto == null){
				tokenDto = new SysUserTokenDto();
				tokenDto.setUserId(user.getId());
				tokenDto.setToken(token);

				tokenDto.setExpireTime(expireTime);
				//保存用户信息
				redisUtils.set(RedisKeys.AUTH_USER + user.getId(), tokenDto);
				//保存token信息
				redisUtils.set(RedisKeys.AUTH_TOKEN + token, user.getId());
			}else{
				//移除旧token信息
				redisUtils.delete(RedisKeys.AUTH_TOKEN + tokenDto.getToken());
				//更新token信息
				tokenDto.setToken(token);
				tokenDto.setExpireTime(expireTime);
				redisUtils.set(RedisKeys.AUTH_USER + user.getId(), tokenDto);
				redisUtils.set(RedisKeys.AUTH_TOKEN + token, user.getId());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return tokenDto;
	}

	/**
	 * 保存用户token
	 * @param sysUserEntity
	 * @param token
	 */
	@Override
	public void saveUserToken(SysUserEntity sysUserEntity, String token) {

		redisUtils.set(RedisKeys.AUTH_USER + sysUserEntity.getId(), sysUserEntity);
		redisUtils.set(RedisKeys.AUTH_TOKEN + token, sysUserEntity.getId());
	}

	/**
	 * 根据token查询用户
	 * @return
	 */
	@Override
	public SysUserEntity getUserByToken(String token) {

		String userIdString = redisUtils.get(RedisKeys.AUTH_TOKEN + token);
		if (StringUtils.isEmpty(userIdString)) {

			throw AuthException.TOKEN_OUT_TIME;
		} else {

			SysUserEntity sysUserEntity = redisUtils.get(RedisKeys.AUTH_USER + userIdString, SysUserEntity.class);

			if (sysUserEntity == null) {

				throw AuthException.TOKEN_OUT_TIME;
			}

			return sysUserEntity;
		}
	}

	/**
	 * 删除用户token
	 * @param sysUserEntity
	 */
	@Override
	public void deleteUserToken(SysUserEntity sysUserEntity) {

		redisUtils.delete(RedisKeys.AUTH_USER + sysUserEntity.getId());
		String token = SecurityUtils.getSubject().getSession().getId().toString();
		if (!StringUtils.isEmpty(token)) {

			redisUtils.delete(RedisKeys.AUTH_TOKEN + token);
		}
	}

	/**
	 * 登出后，清除token
	 * @param userId  用户ID
	 */
	@Override
	public void logout(long userId) {
		SysUserTokenDto tokenDto = redisUtils.get(RedisKeys.AUTH_USER + userId, SysUserTokenDto.class);
		redisUtils.delete(RedisKeys.AUTH_TOKEN + tokenDto.getToken());
		redisUtils.delete(RedisKeys.AUTH_USER + userId);
	}
}
