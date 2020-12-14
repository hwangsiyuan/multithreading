package com.hussein.um;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>Title: UnModifiableIntegerAccumulator</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 5:15 PM
 */
public final class UnModifiableIntegerAccumulator {

    private int init;

    public UnModifiableIntegerAccumulator(int init) {
        this.init = init;
    }

    public UnModifiableIntegerAccumulator add(UnModifiableIntegerAccumulator accumulator, int i) {
        return new UnModifiableIntegerAccumulator(accumulator.getValue() + i);
    }

    public int getValue() {
        return init;
    }

    public static void main(String[] args) {
        UnModifiableIntegerAccumulator ia = new UnModifiableIntegerAccumulator(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue = ia.getValue();
                UnModifiableIntegerAccumulator resultIa = ia.add(ia, inc);
                if (resultIa.getValue() != oldValue + inc) {
                    System.err.println(oldValue + "+" + inc + "=" + (oldValue + inc) + "!=" + resultIa.getValue());
                }
                inc++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread-" + i).start());
    }
}
