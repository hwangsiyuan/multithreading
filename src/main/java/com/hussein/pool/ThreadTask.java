package com.hussein.pool;

/**
 * <p>Title: ThreadTask</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 4:10 PM
 */
public class ThreadTask {

    private Thread thread;

    private InternalTask internalTask;

    public ThreadTask(Thread thread, InternalTask internalTask) {
        this.thread = thread;
        this.internalTask = internalTask;
    }

    public Thread getThread() {
        return thread;
    }

    public InternalTask getInternalTask() {
        return internalTask;
    }
}
