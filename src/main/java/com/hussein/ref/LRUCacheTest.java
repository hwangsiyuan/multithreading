package com.hussein.ref;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: LRUCacheTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 2:57 PM
 */
public class LRUCacheTest {

    public static void main(String[] args) throws InterruptedException {
        LRUCache<Integer, MyReference> cache = new LRUCache<>(200, k -> new MyReference());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache.get(i);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("the " + i + " reference stored at cache.");
        }
    }
}
