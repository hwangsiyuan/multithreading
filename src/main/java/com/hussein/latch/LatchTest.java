package com.hussein.latch;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: LatchTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 8:04 PM
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException, WaitTimeOutException {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Mike", "bus").start();
        new ProgrammerTravel(latch, "Jessie", "car").start();
        new ProgrammerTravel(latch, "LanYi", "subway").start();
        new ProgrammerTravel(latch, "FanHua", "bike").start();
        latch.await(TimeUnit.SECONDS, 2);
        System.out.println("==all of programmer arrived==");
    }
}
