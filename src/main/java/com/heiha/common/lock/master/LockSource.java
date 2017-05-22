package com.heiha.common.lock.master;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 10:32<br>
 * <b>Author:</b> heiha<br>
 *
 * Interface definition which the lock source implementations are supposed to implement.
 */
public interface LockSource<K, V> {
    /**
     * Using to acquire lock.
     *
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    boolean setIfAbsent(K key, V value) throws Exception;

    /**
     * Using to release lock.
     *
     * @param key
     * @throws Exception
     */
    void delete(K key) throws Exception;

    /**
     * Using to check lock.
     *
     * @param key
     * @return
     * @throws Exception
     */
    boolean hasKey(K key) throws Exception;

    /**
     * Using to get lock holder.
     *
     * @param key
     * @return
     * @throws Exception
     */
    V get(K key) throws Exception;
}
