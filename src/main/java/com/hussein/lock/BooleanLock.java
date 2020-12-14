package com.hussein.lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * <p>Title: BooleanLock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 6:45 PM
 */
public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedThreadList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                Thread thread = Thread.currentThread();
                if (!blockedThreadList.contains(thread)) {
                    blockedThreadList.add(thread);
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    blockedThreadList.remove(thread);
                    throw e;
                }
            }
            Thread thread = Thread.currentThread();
            blockedThreadList.remove(thread);
            locked = true;
            this.currentThread = thread;
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + mills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get lock during " + mills);
                    }
                    Thread thread = Thread.currentThread();
                    if (!blockedThreadList.contains(thread)) {
                        blockedThreadList.add(thread);
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName() + " get lock..");
                blockedThreadList.remove(thread);
                locked = true;
                this.currentThread = thread;
            }
        }
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName() + " try release the lock.");
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                locked = false;
                this.notifyAll();
                System.out.println(Thread.currentThread().getName() + " release the lock.");
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreadList() {
        return Collections.unmodifiableList(blockedThreadList);
    }
}
