package com.hussein.observe;

/**
 * <p>Title: Task</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 11:10 AM
 */
@FunctionalInterface
public interface Task<T> {

    T call() throws InterruptedException;
}
