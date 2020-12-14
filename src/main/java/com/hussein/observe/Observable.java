package com.hussein.observe;

/**
 * <p>Title: Observable</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 10:55 AM
 */
public interface Observable {

    enum Cycle {

        STARTED, RUNNING, DONE, ERROR;
    }

    Cycle getCycle();

    void start();

    void interrupt();
}
