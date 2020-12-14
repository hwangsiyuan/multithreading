package com.hussein.hook;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: EmptyExceptionHandler</p>
 * <p>Description: main->system->error.print</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 4:59 PM
 */
public class EmptyExceptionHandler {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup);
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1 / 0);
        }, "thread-1").start();
    }
}
