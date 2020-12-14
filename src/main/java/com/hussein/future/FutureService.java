package com.hussein.future;

/**
 * <p>Title: FutureService</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:36 PM
 */
public interface FutureService<IN, OUT> {

    /**
     * 没有返回值的任务
     */
    Future<?> submit(Runnable runnable);

    /**
     * 有返回值的任务
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    /**
     * 有返回值的任务且有回调的任务
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }

}
