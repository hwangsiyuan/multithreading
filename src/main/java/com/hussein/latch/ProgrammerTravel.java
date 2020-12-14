package com.hussein.latch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ProgrammerTravel</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 8:00 PM
 */
public class ProgrammerTravel extends Thread {

    private final Latch latch;

    private final String name;

    private final String transportation;

    public ProgrammerTravel(Latch latch, String name, String transportation) {
        super(name);
        this.latch = latch;
        this.name = name;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.printf("%s start take the [%s]\n", name, transportation);
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s arrived by %s\n", name, transportation);
        latch.countDown();
    }
}
