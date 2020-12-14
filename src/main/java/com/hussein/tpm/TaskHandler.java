package com.hussein.tpm;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: TaskHandler</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 11:13 AM
 */
public class TaskHandler implements Runnable {

    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("start handler " + request);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish handler " + request);
    }
}
