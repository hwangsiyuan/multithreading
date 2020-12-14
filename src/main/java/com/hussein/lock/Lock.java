package com.hussein.lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * <p>Title: Lock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 6:46 PM
 */
public interface Lock {

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreadList();
}
