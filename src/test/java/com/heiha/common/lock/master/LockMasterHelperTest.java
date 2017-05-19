package com.heiha.common.lock.master;

import org.junit.Before;
import org.junit.Test;
import com.heiha.common.LockMasterApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * <br>
 * <b>Project:</b> lock-master<br>
 * <b>Date:</b> 2017/5/19 11:49<br>
 * <b>Author:</b> heiha<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LockMasterApplication.class)
public class LockMasterHelperTest {
    @Autowired
    private LockMasterHelper helper;

    private String lock;
    private String holder;

    @Before
    public void setUp() throws Exception {
        lock = "test170519";
        holder = "170519";

    }

    @Test
    public void test() throws LockMasterException {
        System.out.println(helper.tryLock(lock, holder));
        System.out.println(helper.isLocked(lock));
        System.out.println(helper.getLockHolder(lock));
        helper.unlock(lock);
        System.out.println(helper.isLocked(lock));
    }

}