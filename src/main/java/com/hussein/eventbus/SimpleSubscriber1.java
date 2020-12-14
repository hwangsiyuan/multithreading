package com.hussein.eventbus;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: SimpleSubscriber1</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 2:56 PM
 */
public class SimpleSubscriber1 {

    @Subscribe
    public void method1(String message) {
        try {
            System.out.println("SimpleSubscriber1 method1 start." + message);
            TimeUnit.SECONDS.sleep(5);
            System.out.println("SimpleSubscriber1 method1 done." + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(topic = "test")
    public void method2(String message) {
        try {
            System.out.println("SimpleSubscriber1 method2 start." + message);
            TimeUnit.SECONDS.sleep(5);
            System.out.println("SimpleSubscriber1 method2 done." + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
