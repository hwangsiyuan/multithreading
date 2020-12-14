package com.hussein.tg;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ThreadGroupInterrupt</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/24 3:25 PM
 */
public class ThreadGroupInterrupt {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("test");
        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t1 is exit..");
        }, "t1").start();
        new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t2 is exit..");
        }, "t2").start();
        TimeUnit.MILLISECONDS.sleep(2);
        group.interrupt();
    }
}
