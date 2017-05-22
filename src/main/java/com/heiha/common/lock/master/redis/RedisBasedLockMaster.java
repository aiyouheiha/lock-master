package com.heiha.common.lock.master.redis;

import com.heiha.common.lock.master.StringLockMaster;
import com.heiha.common.lock.master.memcached.MemcachedHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/22 15:55<br>
 * <b>Author:</b> heiha<br>
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "source-type", havingValue = "redis")
@ConditionalOnBean(RedisHelper.class)
public class RedisBasedLockMaster extends StringLockMaster {
    @Autowired
    private RedisHelper redisHelper;

    @Override
    protected void setLockSource() {
        lockSource = redisHelper;
    }
}
