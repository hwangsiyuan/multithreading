package com.hussein.active;

import com.hussein.future.FutureTask;

/**
 * <p>Title: ActiveFuture</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 4:29 PM
 */
public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
