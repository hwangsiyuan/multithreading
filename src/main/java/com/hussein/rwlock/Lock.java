package com.hussein.rwlock;

/**
 * <p>Title: Lock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 8:10 PM
 */
public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
