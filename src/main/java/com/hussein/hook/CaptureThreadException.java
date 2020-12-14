package com.hussein.hook;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: CaptureThreadException</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 4:48 PM
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception ");
            e.printStackTrace();
        });
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1 / 0);
        }, "thread-1").start();
    }
}
