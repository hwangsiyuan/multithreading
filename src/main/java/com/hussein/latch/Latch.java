package com.hussein.latch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>Title: Latch</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 7:53 PM
 */
public abstract class Latch {

    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    abstract void await() throws InterruptedException;

    abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeOutException;

    abstract void countDown();

    abstract int getUnArrived();
}
