package com.hussein.future;

/**
 * <p>Title: Future</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:33 PM
 */
public interface Future<T> {

    /**
     * 获取future结果
     *
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;

    /**
     * future是否完成
     *
     * @return
     */
    boolean isDone();
}
