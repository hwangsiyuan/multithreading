package com.hussein.wt;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: Worker</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 11:29 AM
 */
public class Worker extends Thread {

    private ProductChannel productChannel;

    public Worker(String name, ProductChannel productChannel) {
        super(name);
        this.productChannel = productChannel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Production production = productChannel.get();
                System.out.println(getName() + " take the product:" + production);
                production.create();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
