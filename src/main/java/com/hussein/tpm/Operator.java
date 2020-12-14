package com.hussein.tpm;

import com.hussein.pool.BasicThreadPool;
import com.hussein.pool.ThreadPool;

/**
 * <p>Title: Operator</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 11:29 AM
 */
public class Operator {

    private final ThreadPool threadPool = new BasicThreadPool(1, 2, 4, 1000);

    public void call(String business) {
        threadPool.execute(new TaskHandler(new Request(business)));
    }
}
