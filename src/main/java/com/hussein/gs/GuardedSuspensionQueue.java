package com.hussein.gs;

import java.util.LinkedList;

/**
 * <p>Title: GuardedSuspensionQueue</p>
 * <p>Description: 确保挂起模式</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 5:35 PM
 */
public class GuardedSuspensionQueue {

    private final LinkedList<Integer> queue = new LinkedList<>();

    private final int limit;

    public GuardedSuspensionQueue(int limit) {
        this.limit = limit;
    }

    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            while (queue.size() >= limit) {
                this.wait();
            }
            queue.addLast(data);
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
