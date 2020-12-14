package com.hussein.active;

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
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.submitOrder("hello",12345);
        System.out.println("hello kitty");
        Thread.currentThread().join();
    }
}
