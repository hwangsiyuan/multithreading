package com.hussein.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>Title: ThreadLocalExample</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/7 5:46 PM
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            threadLocal.set(i);
            System.out.println(Thread.currentThread().getName() + " set value " + i);
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer value = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " get value " + value);

        }, "thread-" + i).start());
    }
}
