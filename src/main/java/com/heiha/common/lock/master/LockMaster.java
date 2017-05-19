package com.heiha.common.lock.master;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 10:21<br>
 * <b>Author:</b> heiha<br>
 */
public interface LockMaster<L, H> {
    /**
     * Acquires the lock without blocking, successful return true. <br>
     * Lock is NON re-entrant.
     *
     * @param lock
     * @param holder
     * @return true/false
     * @throws LockMasterException
     */
    boolean tryLock(L lock, H holder) throws LockMasterException;

    /**
     * Releases the lock.
     * @param lock
     * @throws LockMasterException
     */
    void unlock(L lock) throws LockMasterException;

    /**
     * Check lock status, locked return true.
     * @return true/false
     * @throws LockMasterException
     */
    boolean isLocked(L lock) throws LockMasterException;

    /**
     * Get the lock holder.
     * @param lock
     * @return Lock holder
     * @throws LockMasterException
     */
    H getLockHolder(L lock) throws LockMasterException;
}
