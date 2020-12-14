package com.hussein.pool;

/**
 * <p>Title: RunnableQueue</p>
 * <p>Description: 自定义线程池队列</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:03 PM
 */
public interface RunnableQueue {

    /**
     * 向队列中提交任务
     *
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 从队列中获取任务
     *
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 队列大小
     *
     * @return
     */
    int size();
}
