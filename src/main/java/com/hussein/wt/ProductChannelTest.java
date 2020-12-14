package com.hussein.wt;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * <p>Title: ProductChannelTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 11:54 AM
 */
public class ProductChannelTest {

    public static void main(String[] args) {
        ProductChannel channel = new ProductChannel(5);
        AtomicInteger productNo = new AtomicInteger();
        IntStream.range(0, 8).forEach(i -> new Thread(() -> {
            try {
                while (true){
                    channel.offer(new Production("product-" + productNo.getAndIncrement()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }
}
