package com.hussein;

import com.hussein.lock.CLHLock;
import com.hussein.lock.TicketLockV2;
import com.hussein.lock.Tools;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: LockTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 11:16 AM
 */
@SuppressWarnings("all")
public class LockTest {

    private TicketLockV2 ticketLockV2 = new TicketLockV2();

    @Test
    public void testTicketLockV2() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(this::ticketLockV2Test);
        executorService.execute(this::ticketLockV2Test);
        executorService.execute(this::ticketLockV2Test);
    }

    private void ticketLockV2Test() {
        Thread currentThread = Thread.currentThread();
        try {
            ticketLockV2.lock();
            System.out.println(currentThread.getName() + "获取锁成功");
        } finally {
            ticketLockV2.unlock();
            System.out.println(currentThread.getName() + "释放锁成功");
        }
    }

    private CLHLock clhLock = new CLHLock();

    @Test
    public void testCLHLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(this::CLHLockTest);
        executorService.execute(this::CLHLockTest);
        executorService.execute(this::CLHLockTest);
        executorService.execute(this::CLHLockTest);
        Thread.sleep(10000L);
    }

    private void CLHLockTest() {
        Thread currentThread = Thread.currentThread();
        try {
            clhLock.lock();
            System.out.println(currentThread.getName() + "获取锁成功");
            Tools.pause();
        } finally {
            clhLock.unlock();
            System.out.println(currentThread.getName() + "释放锁成功");
        }
    }
}
