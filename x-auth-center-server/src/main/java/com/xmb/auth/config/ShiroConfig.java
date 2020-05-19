package com.xmb.auth.config;

import com.xmb.auth.oauth2.CustomSessionManager;
import com.xmb.auth.oauth2.OAuth2Filter;
import com.xmb.auth.oauth2.RedisSessionDao;
import com.xmb.auth.oauth2.UserRealm;
import com.xmb.auth.utils.RedisKeys;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import javax.servlet.Filter;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@Configuration
public class ShiroConfig {

    /**
     * @RequiresPermissions这类shiro权限的注解，是DefaultAdvisorAutoProxyCreator这个bean设置之后才会生效的
     * 代理生成器，需要借助 SpringAOP 来扫描 @RequiresRoles 和 @RequiresPermissions 等注解，生成代理类实现功能增强，从而实现权限控制。需要配合 AuthorizationAttributeSourceAdvisor 一起使用，否则权限注解无效。
     *
     * @return
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 上面配置的 DefaultAdvisorAutoProxyCreator 相当于一个切面，下面这个类就相当于切点了，两个一起才能实现注解权限控制。
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 凭证匹配器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public CustomSessionManager defaultWebSessionManager(RedisSessionDao redisSessionDao) {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setGlobalSessionTimeout(RedisKeys.expireTime * 1000);
        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * 自定义realm
     *
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    //自定义sessionmanager注入
    @Bean
    public SessionManager sessionManager() {

        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }

    /**
     * 安全管理器
     * 注：使用shiro-spring-boot-starter 1.4时，返回类型是SecurityManager会报错，直接引用shiro-spring则不报错
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(RedisSessionDao redisSessionDao) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        securityManager.setRememberMeManager(null);
        securityManager.setSessionManager(defaultWebSessionManager(redisSessionDao));
        return securityManager;
    }

    /**
     * 设置过滤规则
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilterFactoryBean.setFilters(filters);

        //注意此处使用的是LinkedHashMap，是有顺序的，shiro会按从上到下的顺序匹配验证，匹配了就不再继续验证
        //所以上面的url要苛刻，宽松的url要放在下面，尤其是"/**"要放到最下面，如果放前面的话其后的验证规则就没作用了。
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/auth/sysuser/login", "anon");
        filterChainDefinitionMap.put("/auth/sysuser/checkUserMobilePassword", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
