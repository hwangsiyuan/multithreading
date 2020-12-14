package com.hussein.rwlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ShareData</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 4:39 PM
 */
public class ShareData {

    public final List<Character> container = new ArrayList<>();

    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add('c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            readLock.lock();
            char[] readBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                readBuffer[i] = container.get(i);
            }
            TimeUnit.SECONDS.sleep(1);
            return readBuffer;
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                container.add(i, c);
            }
            TimeUnit.SECONDS.sleep(1);
        } finally {
            writeLock.unlock();
        }
    }
}
