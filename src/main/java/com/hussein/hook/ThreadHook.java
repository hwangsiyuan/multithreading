package com.hussein.hook;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ThreadHook</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 5:06 PM
 */
public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("the hook thread 1 is running..");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 1 is exit..");
        }));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("the hook thread 2 is running..");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread 2 is exit..");
        }));
        System.out.println("the program is stopping...");
    }
}
