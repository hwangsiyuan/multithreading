package com.hussein.future;

/**
 * <p>Title: Callback</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 5:08 PM
 */
@FunctionalInterface
public interface Callback<T> {

    void call(T t);
}
