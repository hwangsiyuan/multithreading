package com.hussein.active;

/**
 * <p>Title: OrderServiceFactory</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 5:27 PM
 */
public class OrderServiceFactory {

    private static final ActiveMessageQueue QUEUE = new ActiveMessageQueue();

    private OrderServiceFactory() {
    }

    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, QUEUE);
    }
}
