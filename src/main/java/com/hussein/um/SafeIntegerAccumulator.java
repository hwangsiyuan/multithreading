package com.hussein.um;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>Title: SafeIntegerAccumulator</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 5:15 PM
 */
public class SafeIntegerAccumulator {

    private int init;

    public SafeIntegerAccumulator(int init) {
        this.init = init;
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return init;
    }

    public static void main(String[] args) {
        SafeIntegerAccumulator ia = new SafeIntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue;
                int result;
                synchronized (SafeIntegerAccumulator.class) {
                    oldValue = ia.getValue();
                    result = ia.add(inc);
                    if (result != oldValue + inc) {
                        System.err.println(oldValue + "+" + inc + "=" + (oldValue + inc) + "!=" + result);
                    }
                    inc++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread-" + i).start());
    }
}
