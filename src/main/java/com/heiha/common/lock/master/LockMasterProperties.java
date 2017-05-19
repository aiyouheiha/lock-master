package com.heiha.common.lock.master;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 10:52<br>
 * <b>Author:</b> heiha<br>
 */
@ConditionalOnProperty(prefix = "lock.master", name = "enable", havingValue = "true")
@ConfigurationProperties(prefix = "lock.master")
public class LockMasterProperties {
    private String sourceType;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
