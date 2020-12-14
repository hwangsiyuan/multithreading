package com.hussein.monitor;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ThisMonitor</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 4:04 PM
 */
@SuppressWarnings("all")
public class ThisMonitor {

    public synchronized void methodOne() throws InterruptedException {
        System.out.println(this + "-methodOne is running..");
        TimeUnit.MINUTES.sleep(5);
    }

    public synchronized void methodTwo() throws InterruptedException {
        System.out.println(this + "-methodTwo is running..");
        TimeUnit.MINUTES.sleep(5);
    }

    public void methodThree() throws InterruptedException {
        synchronized (this) {
            System.out.println(this + "-methodThree is running..");
            TimeUnit.MINUTES.sleep(5);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                ThisMonitor monitor = new ThisMonitor();
                try {
                    monitor.methodOne();
                    monitor.methodTwo();
                    monitor.methodThree();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
