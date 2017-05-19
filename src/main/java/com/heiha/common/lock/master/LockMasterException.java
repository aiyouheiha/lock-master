package com.heiha.common.lock.master;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 10:23<br>
 * <b>Author:</b> heiha<br>
 */
public class LockMasterException extends Exception {
    public LockMasterException() {
    }

    public LockMasterException(String message) {
        super(message);
    }

    public LockMasterException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockMasterException(Throwable cause) {
        super(cause);
    }

    public LockMasterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
