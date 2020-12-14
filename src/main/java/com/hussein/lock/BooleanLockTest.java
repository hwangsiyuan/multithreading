package com.hussein.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * <p>Title: BooleanLockTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 10:34 AM
 */
public class BooleanLockTest {

    private final BooleanLock blk = new BooleanLock();

    public void synMethod() throws InterruptedException, TimeoutException {
        try {
            blk.lock(20000L);
            System.out.println(Thread.currentThread().getName() + " do something..");
            TimeUnit.SECONDS.sleep(1);
        } finally {
            blk.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blkt = new BooleanLockTest();
        IntStream.range(0, 10).mapToObj(i -> {
            return new Thread(() -> {
                try {
                    blkt.synMethod();
                } catch (InterruptedException | TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }).forEach(Thread::start);
    }
}
