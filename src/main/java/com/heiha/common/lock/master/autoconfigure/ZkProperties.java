package com.heiha.common.lock.master.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <b>Project:</b> lock-master
 * <b>Date:</b> 2017-05-15 01:36
 * <b>Author:</b> heiha
 */
@ConfigurationProperties("zk")
public class ZkProperties {
    private String connectString = "localhost:2181";
    private String namespace;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
