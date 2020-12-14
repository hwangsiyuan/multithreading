package com.hussein.pool;

/**
 * <p>Title: ThreadPool</p>
 * <p>Description: 自定义线程池</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 1:57 PM
 */
public interface ThreadPool {

    /**
     * 提交任务到线程池
     *
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 线程池初始大小
     *
     * @return
     */
    int getInitSize();

    /**
     * 核心线程数
     *
     * @return
     */
    int getCoreSize();

    /**
     * 最大线程数
     *
     * @return
     */
    int getMaxSize();

    /**
     * 活跃线程数
     *
     * @return
     */
    int getActiveCount();

    /**
     * 线程队列大小
     *
     * @return
     */
    int getQueueSize();

    /*8
    关闭线程池
     */
    void shutdown();

    /**
     * 线程池是否已关闭
     *
     * @return
     */
    boolean isShutdown();
}
