package com.hussein;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: FutureTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/9/16 5:09 PM
 */
public class FutureTest {

    @Test
    public void testFuture() {
        long begin = System.currentTimeMillis();
        List<String> result = new ArrayList<>();
        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return "1";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return "2";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        try {
            result.add(c1.get());
            result.add(c2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin + ":" + result);
    }

}
