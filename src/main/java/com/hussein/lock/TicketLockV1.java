package com.hussein.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: TicketLockV1</p>
 * <p>Description: 模拟叫号窗口，只有窗口叫的号与自己的持有的号码一致时获取锁。</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 10:48 AM
 */
public class TicketLockV1 {

    private AtomicInteger serviceNum = new AtomicInteger();

    private AtomicInteger ticketNum = new AtomicInteger();

    public int lock() {
        int currentTicketNum = ticketNum.getAndIncrement();
        while (currentTicketNum != serviceNum.get()) {
            //DO nothing
        }
        return currentTicketNum;
    }

    public void unlock(int currentTicketNum) {
        //处理完党倩浩，窗口的号码+1
        serviceNum.compareAndSet(currentTicketNum, currentTicketNum + 1);
    }
}
