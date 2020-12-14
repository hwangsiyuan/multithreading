package com.hussein.future;

/**
 * <p>Title: FutureTask</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:39 PM
 */
public class FutureTask<T> implements Future<T> {

    /**
     * 任务结果
     */
    private T result;

    /**
     * 任务是否完成
     */
    private boolean isDone = false;

    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            while (!isDone) {
                LOCK.wait();
            }
        }
        return result;
    }

    public void finish(T result) {
        synchronized (LOCK) {
            if (isDone) {
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
