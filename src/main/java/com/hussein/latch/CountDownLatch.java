package com.hussein.latch;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: CountDownLatch</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 7:55 PM
 */
public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeOutException {
        if (time <= 0) {
            throw new IllegalArgumentException("invalid time.");
        }
        long remaining = unit.toNanos(time);
        final long endNanos = System.nanoTime() + remaining;
        synchronized (this) {
            while (limit > 0) {
                if (TimeUnit.NANOSECONDS.toMillis(remaining) <= 0) {
                    throw new WaitTimeOutException(Thread.currentThread().getName() + " wait time over specify " + time + " " + unit.toString());
                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remaining));
                remaining = endNanos - System.nanoTime();
            }
        }
    }

    @Override
    void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of tasks arrived.");
            }
            limit--;
            this.notifyAll();
        }
    }

    @Override
    int getUnArrived() {
        return limit;
    }
}
