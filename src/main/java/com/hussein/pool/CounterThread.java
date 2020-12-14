package com.hussein.pool;

/**
 * <p>Title: CounterThread</p>
 * <p>Description: 线程池数量计算线程</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 4:13 PM
 */
public class CounterThread extends Thread {

    private final BasicThreadPool threadPool;

    private int coreSize;

    private int maxSize;

    public CounterThread(BasicThreadPool threadPool) {
        super("counter-thread");
        this.threadPool = threadPool;
        this.coreSize = threadPool.getCoreSize();
        this.maxSize = threadPool.getMaxSize();
    }

    @Override
    public void run() {
        while (!threadPool.isShutdown() && !isInterrupted()) {
            try {
                threadPool.getTimeUnit().sleep(threadPool.getKeepAliveTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (threadPool) {
                if (threadPool.isShutdown()) {
                    break;
                }
                RunnableQueue runnableQueue = threadPool.getRunnableQueue();
                //如果任务队列不为空 且活动线程小于核心线程数继续扩容
                if (runnableQueue.size() > 0 && threadPool.getActiveCount() < coreSize) {
                    for (int i = threadPool.getInitSize(); i < coreSize; i++) {
                        threadPool.newThread();
                    }
                    //防止一次扩容到max
                    continue;
                }
                //如果任务队列不为空 且活动线程小于最大线程数继续扩容
                if (runnableQueue.size() > 0 && threadPool.getActiveCount() < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        threadPool.newThread();
                    }
                }
                //如果任务队列中没有任务 回收线程队列到 coreSize
                if (runnableQueue.size() == 0 && threadPool.getActiveCount() > coreSize) {
                    for (int i = coreSize; i < threadPool.getActiveCount(); i++) {
                        threadPool.removeThread();
                    }
                }
            }
        }
    }
}
