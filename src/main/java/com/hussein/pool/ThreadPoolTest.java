package com.hussein.pool;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>Title: ThreadPoolTest</p>
 * <p>Description: 自定义线程池测试</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/25 5:15 PM
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new BasicThreadPool(2, 4, 6, 1000);
        IntStream.range(0, 20).forEach(i -> {
            threadPool.execute(new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is running and done...");
            }
            ));
        });
        for (; ; ) {
            System.out.println("\n++++++++++++++++");
            System.out.println("+activeCount: "+threadPool.getActiveCount());
            System.out.println("+queueSize: "+threadPool.getQueueSize());
            System.out.println("+coreSize: "+threadPool.getCoreSize());
            System.out.println("+maxSize: "+threadPool.getMaxSize());
            System.out.println("++++++++++++++++\n");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
