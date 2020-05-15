package com.xmb.auth.oauth2;

import com.alibaba.fastjson.JSON;
import com.xmb.auth.utils.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Ben
 * @date 2020-05-16
 * @desc
 */
@Slf4j
@Component
public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
//        redisTemplate.opsForValue().set(session.getId(), session, RedisKeys.expireTime, TimeUnit.SECONDS);

        log.info("RedisSessionDao doCreate sessionId = {}", session.getId());
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {

        log.info("RedisSessionDao doReadSession sessionId = {}", sessionId.toString());
        return sessionId == null ? null : (Session) redisTemplate.opsForValue().get(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

        log.info("RedisSessionDao update session = {}", JSON.toJSONString(session));
        if (session != null && session.getId() != null) {
            session.setTimeout(RedisKeys.expireTime * 1000);
//            redisTemplate.opsForValue().set(session.getId(), session, RedisKeys.expireTime, TimeUnit.SECONDS);
        }
    }

    @Override
    public void delete(Session session) {

        log.info("RedisSessionDao delete session = {}", JSON.toJSONString(session));
        if (session != null && session.getId() != null) {
            redisTemplate.opsForValue().getOperations().delete(session.getId());
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {

        Set keys = redisTemplate.keys("*");
        log.info("RedisSessionDao getActiveSessions keys = {}", JSON.toJSONString(keys));
        return keys;
    }

}