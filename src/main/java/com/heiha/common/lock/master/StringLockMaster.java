package com.heiha.common.lock.master;

import com.heiha.common.lock.master.LockMaster;
import com.heiha.common.lock.master.LockMasterException;
import com.heiha.common.lock.master.LockSource;
import org.springframework.beans.factory.InitializingBean;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 11:21<br>
 * <b>Author:</b> heiha<br>
 */
public abstract class StringLockMaster implements LockMaster<String, String>, InitializingBean {
    protected LockSource<String, String> lockSource;

    protected abstract void setLockSource();

    @Override
    public void afterPropertiesSet() throws Exception {
        setLockSource();
    }

    @Override
    public boolean tryLock(String lock, String holder) throws LockMasterException {
        try {
            return lockSource.setIfAbsent(lock, holder);
        } catch (Exception e) {
            throw new LockMasterException("Error when \"" + holder + "\" acquire the lock: " + lock);
        }
    }

    @Override
    public void unlock(String lock) throws LockMasterException {
        try {
            lockSource.delete(lock);
        } catch (Exception e) {
            throw new LockMasterException("Error when release the lock: " + lock);
        }
    }

    @Override
    public boolean isLocked(String lock) throws LockMasterException {
        try {
            return lockSource.hasKey(lock);
        } catch (Exception e) {
            throw new LockMasterException("Error when check the lock: " + lock);
        }
    }

    @Override
    public String getLockHolder(String lock) throws LockMasterException {
        try {
            return lockSource.get(lock);
        } catch (Exception e) {
            throw new LockMasterException("Error when get the lock holder, lock: " + lock);
        }
    }
}
