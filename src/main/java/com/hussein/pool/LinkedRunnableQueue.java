package com.hussein.pool;

import java.util.LinkedList;

/**
 * <p>Title: LinkedRunnableQueue</p>
 * <p>Description: 自定义队列</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:32 PM
 */
public class LinkedRunnableQueue implements RunnableQueue {

    /**
     * 队列最大容量
     */
    private int limit;

    /**
     * 拒绝策略
     */
    private DenyPolicy denyPolicy;

    /**
     * 线程池
     */
    private ThreadPool threadPool;

    /**
     * 任务队列
     */
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList) {
            return runnableList.size();
        }
    }
}
