package com.hussein.rwlock;

/**
 * <p>Title: ReadLock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 4:18 PM
 */
public class ReadLock implements Lock {

    private ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            //如果有线程正在读取或者偏向为写锁时存在等在写的线程 读线程被挂起
            while (readWriteLock.getWritingWriters() > 0 || (readWriteLock.isPreferWriters() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            //读线程+1
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            //读线程-1
            readWriteLock.decrementReadingReaders();
            //偏向为写锁
            readWriteLock.changePrefer(true);
            //通知其他线程可以去 争取锁
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
