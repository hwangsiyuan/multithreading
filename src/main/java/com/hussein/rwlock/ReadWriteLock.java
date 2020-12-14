package com.hussein.rwlock;

/**
 * <p>Title: ReadWriteLock</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 8:12 PM
 */
public interface ReadWriteLock {

    /**
     * 写锁
     *
     * @return
     */
    Lock writeLock();

    /**
     * 读锁
     *
     * @return
     */
    Lock readLock();

    /**
     * 正在写的线程数
     *
     * @return
     */
    int getWritingWriters();

    /**
     * 等待写的线程数
     *
     * @return
     */
    int getWaitingWriters();


    /**
     * 正在读的线程
     *
     * @return
     */
    int getReadingReaders();


    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriters) {
        return new ReadWriteLockImpl(preferWriters);
    }

}
