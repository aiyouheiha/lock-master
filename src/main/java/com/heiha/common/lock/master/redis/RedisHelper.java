package com.heiha.common.lock.master.redis;

import com.heiha.common.lock.master.LockSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/22 15:56<br>
 * <b>Author:</b> heiha<br>
 *
 * Using Spring Boot autoconfigure, ensure no properties set in profile if it is unused. <br>
 * ConditionalOnProperty annotation is optional, when redis is not only used on lock.
 *
 * @see org.springframework.boot.autoconfigure.data.redis.RedisProperties
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "source-type", havingValue = "redis")
public class RedisHelper implements LockSource<String, String> {
    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public boolean setIfAbsent(String key, String value) throws Exception {
        return template.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void delete(String key) throws Exception {
        template.delete(key);
    }

    @Override
    public boolean hasKey(String key) throws Exception {
        return template.hasKey(key);
    }

    @Override
    public String get(String key) throws Exception {
        return template.opsForValue().get(key);
    }
}
