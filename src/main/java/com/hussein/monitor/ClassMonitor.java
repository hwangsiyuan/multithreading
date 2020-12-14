package com.hussein.monitor;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ClassMonitor</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 4:12 PM
 */
@SuppressWarnings("all")
public class ClassMonitor {

    public static synchronized void methodOne() throws InterruptedException {
        System.out.println("methodOne is running..");
        TimeUnit.MINUTES.sleep(5);
    }

    public static synchronized void methodTwo() throws InterruptedException {
        System.out.println("methodTwo is running..");
        TimeUnit.MINUTES.sleep(5);
    }

    public static void methodThree() throws InterruptedException {
        synchronized (ClassMonitor.class) {
            System.out.println("methodThree is running..");
            TimeUnit.MINUTES.sleep(5);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                ClassMonitor monitor = new ClassMonitor();
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
