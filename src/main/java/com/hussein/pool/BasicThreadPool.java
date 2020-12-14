package com.hussein.pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: BasicThreadPool</p>
 * <p>Description: 基础线程池</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:57 PM
 */
public class BasicThreadPool implements ThreadPool {

    private final int initSize;

    private final int coreSize;

    private final int maxSize;

    private int activeCount;

    private final RunnableQueue runnableQueue;

    private final ThreadFactory threadFactory;

    private final DenyPolicy denyPolicy;

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final CounterThread counterThread;

    private volatile Boolean isShutdown = false;

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize) {
        this(initSize, coreSize, maxSize, queueSize, DEFAULT_THREAD_FACTORY, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int coreSize, int maxSize, int queueSize, ThreadFactory threadFactory, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.threadFactory = threadFactory;
        this.denyPolicy = denyPolicy;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.counterThread = new CounterThread(this);
        this.init();
    }

    private void init() {
        counterThread.start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    public void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        activeCount++;
        thread.start();
    }

    public void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.getInternalTask().stop();
        activeCount--;
    }

    public RunnableQueue getRunnableQueue() {
        return runnableQueue;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public void execute(Runnable runnable) {
        checkState();
        runnableQueue.offer(runnable);
    }

    @Override
    public int getInitSize() {
        checkState();
        return initSize;
    }

    @Override
    public int getCoreSize() {
        checkState();
        return coreSize;
    }

    @Override
    public int getMaxSize() {
        checkState();
        return maxSize;
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return activeCount;
        }
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    @Override
    public int getQueueSize() {
        checkState();
        return runnableQueue.size();
    }

    private void checkState() {
        if (isShutdown) {
            throw new IllegalStateException("the thread pool is destroy.");
        }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.getInternalTask().stop();
                threadTask.getThread().interrupt();
            });
            counterThread.interrupt();
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }
}
