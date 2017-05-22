package com.heiha.common.lock.master.memcached;

import com.heiha.common.lock.master.LockSource;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/18 11:08<br>
 * <b>Author:</b> heiha<br>
 *
 * ConditionalOnProperty annotation is optional, when memcached is not only used on lock.
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "source-type", havingValue = "memcached")
@EnableConfigurationProperties(MemcachedProperties.class)
public class MemcachedHelper implements LockSource<String, String>, InitializingBean {
    @Autowired
    private MemcachedProperties properties;

    private MemcachedClient client;

    @Override
    public void afterPropertiesSet() throws Exception {
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(properties.getHost());
        builder.setConnectionPoolSize(10);
        client = builder.build();
    }

    @Override
    public boolean setIfAbsent(String key, String value) throws Exception {
        return client.set(key, 0, value);
    }

    @Override
    public void delete(String key) throws Exception {
        client.delete(key);
    }

    @Override
    public boolean hasKey(String key) throws Exception {
        return get(key) != null;
    }

    @Override
    public String get(String key) throws Exception {
        return client.get(key);
    }
}
