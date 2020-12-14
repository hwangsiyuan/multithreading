package com.hussein.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: TicketLockV2</p>
 * <p>Description: 模拟叫号窗口，只有窗口叫的号与自己的持有的号码一致时获取锁。
 * 获取锁时返回号码用于解锁，为防止该号码被修改记到ThreadLocal中
 * </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/17 11:02 AM
 */
public class TicketLockV2 {

    private AtomicInteger serviceNum = new AtomicInteger();

    private AtomicInteger ticketNum = new AtomicInteger();

    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<>();

    public void lock() {
        int currentTicketNum = ticketNum.getAndIncrement();
        ticketNumHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()) {

        }
    }

    public void unlock() {
        Integer currentTicketNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTicketNum, currentTicketNum + 1);
    }
}
