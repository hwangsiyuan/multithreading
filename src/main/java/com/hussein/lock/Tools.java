package com.hussein.lock;

import java.util.Random;

/**
 * <p>Title: Tools</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/11 7:27 PM
 */
public class Tools {

    public static void pause() {
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
