package com.hussein.pool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: DefaultThreadFactory</p>
 * <p>Description: 自定义线程工厂</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 2:51 PM
 */
public class DefaultThreadFactory implements ThreadFactory {

    private final ThreadGroup group = new ThreadGroup("thread-pool-group");

    private final AtomicInteger poolNum = new AtomicInteger(1);

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private String namePrefix;

    public DefaultThreadFactory() {
        this.namePrefix = "thread-pool-" + poolNum.getAndIncrement() + "#";
    }

    @Override
    public Thread createThread(Runnable runnable) {
        return new Thread(group, runnable, namePrefix + threadNum.getAndIncrement());
    }
}
