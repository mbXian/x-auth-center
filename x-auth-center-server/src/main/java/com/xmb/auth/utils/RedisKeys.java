package com.xmb.auth.utils;

/**
 * @author 李超
 * @version 1.0
 * Created in 2019/7/31 8:21
 */
public class RedisKeys {

    /**
     * 系统用户
     */
    public static final String AUTH_USER = "auth_user_";
    public static final String AUTH_TOKEN = "auth_token_";

    /**
     * session过期时间
     */
    public static long expireTime = 60;

    /**
     * 获取配置文件信息
     * @param key
     * @return
     */
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    /**
     * 获取应用-角色的权限
     * @param applicationId 应用id
     * @param role 角色id
     * @return rediskey
     */
    public static String getApplicationPermissions(String applicationId, String role){
        StringBuilder sb = new StringBuilder();
        sb.append(AUTH_USER);
        sb.append("_perm");
        sb.append(applicationId);
        sb.append("_");
        sb.append(role);
        return sb.toString();
    }
}
