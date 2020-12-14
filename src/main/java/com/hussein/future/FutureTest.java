package com.hussein.future;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: FutureTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/6 4:55 PM
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i am finish done.");
        });
        System.out.println("hello");
        future.get();
        System.out.println("world");
        FutureService<String, Integer> service1 = FutureService.newService();
        Future<Integer> future1 = service1.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i am finish done.1");
            return input.length();
        }, "hello");
        System.out.println("hello1");
        System.out.println(future1.get());
        System.out.println("world1");
        FutureService<String, Integer> service3 = FutureService.newService();
        service3.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i am finish done.2");
            return input.length();
        }, "hello", System.out::println);
    }
}
