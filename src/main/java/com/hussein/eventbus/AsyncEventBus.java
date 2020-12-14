package com.hussein.eventbus;

import java.util.concurrent.Executor;

/**
 * <p>Title: AsyncEventBus</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 2:30 PM
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus() {
        this("default-async", Dispatcher.PER_THREAD_EXECUTOR, null);
    }

    public AsyncEventBus(EventExceptionHandler handler) {
        this("default-async", Dispatcher.PER_THREAD_EXECUTOR, handler);
    }

    public AsyncEventBus(String busName, Executor executor, EventExceptionHandler handler) {
        super(busName, executor, handler);
    }
}
