package com.hussein.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: FutureServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:44 PM
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public Future<?> submit(Runnable runnable) {
        FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            futureTask.finish(result);
            if (callback != null) {
                callback.call(result);
            }
        }, getNextName()).start();
        return futureTask;
    }

    public String getNextName() {
        return "FUTURE-" + atomicInteger.getAndIncrement();
    }
}
