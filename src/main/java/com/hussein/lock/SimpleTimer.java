package com.hussein.lock;

import java.util.Random;

/**
 * <p>Title: SimpleTimer</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/10 8:59 PM
 */
public class SimpleTimer {

    public static void main(String[] args) {
        Random r = new Random();
        new SimpleTimer().start(r.nextInt(10));
    }

    private void start(int time) {
        System.out.println(time);
        System.out.println(System.currentTimeMillis() / 1000);
        while (time > 0) {
            time--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println("Done!");
    }
}
