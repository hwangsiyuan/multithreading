package com.hussein.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Title: SpinLock</p>
 * <p>Description: 自旋锁：优点线程不用切换状态，即从内核态到用户态的切换，也不会产生线程阻塞及上下文切换;
 *  缺点是如果某个线程持有锁的时间过长，可能别的线程会产生饥饿状态，而且一直尝试获取锁可能会严重消耗CPU性能。
 * </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 10:17 AM
 */
public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!cas.compareAndSet(null, currentThread)) {
            //DO nothing
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        cas.compareAndSet(currentThread, null);
    }

}
