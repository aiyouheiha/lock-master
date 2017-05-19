package com.heiha.common.lock.master.impl.source;

import com.heiha.common.lock.master.LockSource;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/12 17:02<br>
 * <b>Author:</b> heiha<br>
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "source-type", havingValue = "zookeeper")
@EnableConfigurationProperties(ZkProperties.class)
public class ZkHelper implements LockSource<String, String>, InitializingBean {
    @Autowired
    private ZkProperties properties;

    private  CuratorFramework client;

    private InterProcessMutex lock;

    @Override
    public void afterPropertiesSet() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(properties.getConnectString())
                .retryPolicy(retryPolicy)
                .namespace(properties.getNamespace())
                .build();
        client.start();
        lock = new InterProcessMutex(client, "/lock");
    }

    @Override
    public boolean setIfAbsent(String key, String value) throws Exception {
        return client.setData().forPath(keyPath(key), value.getBytes()) != null;
    }

    @Override
    public void delete(String key) throws Exception {
        client.delete().forPath(keyPath(key));
    }

    @Override
    public boolean hasKey(String key) throws Exception {
        return client.checkExists().forPath(keyPath(key)) != null;
    }

    @Override
    public String get(String key) throws Exception {
        return new String(client.getData().forPath(keyPath(key)));
    }

    private String keyPath(String key) {
        return "/" + key;
    }
}
