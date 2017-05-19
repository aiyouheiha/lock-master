package com.heiha.common.lock.master.impl;

import com.heiha.common.lock.master.StringLockMaster;
import com.heiha.common.lock.master.impl.source.ZkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 11:36<br>
 * <b>Author:</b> heiha<br>
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "source-type", havingValue = "zookeeper")
@ConditionalOnBean(ZkHelper.class)
public class ZkBasedLockMaster extends StringLockMaster {
    @Autowired
    private ZkHelper zkHelper;

    @Override
    protected void setLockSource() {
        lockSource = zkHelper;
    }
}
