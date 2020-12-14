package com.hussein.rwlock;

/**
 * <p>Title: ReadWriteLockImpl</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 4:02 PM
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int writingWriters = 0;

    private int waitingWriters = 0;

    private int readingReaders = 0;

    private boolean preferWriters;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriters) {
        this.preferWriters = preferWriters;
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public int getWritingWriters() {
        return writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    public void incrementWritingWriters() {
        this.writingWriters++;
    }

    public void decrementWritingWriters() {
        this.writingWriters--;
    }

    public void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    public void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    public void incrementReadingReaders() {
        this.readingReaders++;
    }

    public void decrementReadingReaders() {
        this.readingReaders--;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean isPreferWriters() {
        return preferWriters;
    }

    public void changePrefer(boolean preferWriters) {
        this.preferWriters = preferWriters;
    }
}
