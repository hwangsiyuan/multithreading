package com.hussein.tg;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ThreadGroupDaemon</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 3:37 PM
 */
public class ThreadGroupDaemon {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("group1");
        ThreadGroup group2 = new ThreadGroup("group2");
        new Thread(group1, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread1").start();
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group2-thread2").start();
        group2.setDaemon(true);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("group1 is destroyed:" + group1.isDestroyed());
        System.out.println("group2 is destroyed:" + group2.isDestroyed());
    }
}
