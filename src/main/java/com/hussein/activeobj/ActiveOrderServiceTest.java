package com.hussein.activeobj;

import com.hussein.active.OrderService;
import com.hussein.future.Future;

/**
 * <p>Title: ActiveOrderServiceTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 5:30 PM
 */
public class ActiveOrderServiceTest {

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
        Future<String> future = orderService.getOrderById(1323254);
        System.out.println("hello kitty");
        System.out.println(future.get());
    }
}
