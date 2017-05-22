package com.heiha.common.lock.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 11:46<br>
 * <b>Author:</b> heiha<br>
 */
@Component
@ConditionalOnProperty(prefix = "lock.master", name = "enable", havingValue = "true")
public class LockMasterHelper implements LockMaster<String, String> {
    @Autowired
    private StringLockMaster stringLockMaster;

    @Override
    public boolean tryLock(String lock, String holder) throws LockMasterException {
        return stringLockMaster.tryLock(lock, holder);
    }

    @Override
    public void unlock(String lock) throws LockMasterException {
        stringLockMaster.unlock(lock);
    }

    @Override
    public boolean isLocked(String lock) throws LockMasterException {
        return stringLockMaster.isLocked(lock);
    }

    @Override
    public String getLockHolder(String lock) throws LockMasterException {
        return stringLockMaster.getLockHolder(lock);
    }
}
