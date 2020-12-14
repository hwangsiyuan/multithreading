package com.hussein.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Title: ReentrantSpinLock</p>
 * <p>Description: 可重入自旋锁</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 10:30 AM
 */
public class ReentrantSpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<>();

    private int count;

    public void lock() {
        Thread currentThread = Thread.currentThread();
        //当前线程已经获取锁 计数+1
        if (currentThread == cas.get()) {
            count++;
            return;
        }
        //当预期为空时可获取锁
        while (!cas.compareAndSet(null, currentThread)) {
            //DO nothing
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == cas.get()) {
            if (count > 0) {
                count--;
            } else {
                cas.compareAndSet(currentThread, null);
            }
        }
    }
}
