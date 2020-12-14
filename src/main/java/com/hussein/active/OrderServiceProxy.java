package com.hussein.active;

import com.hussein.future.Future;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: OrderServiceProxy</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 5:09 PM
 */
public class OrderServiceProxy implements OrderService {

    private final OrderService orderService;

    private final ActiveMessageQueue queue;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue queue) {
        this.orderService = orderService;
        this.queue = queue;
    }

    @Override
    public Future<String> getOrderById(long orderId) {
        ActiveFuture<String> activeFuture = new ActiveFuture<>();
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("activeFuture", activeFuture);
        MethodMessage methodMessage = new GetOrderDetailMessage(params, orderService);
        queue.offer(methodMessage);
        return activeFuture;
    }

    @Override
    public void submitOrder(String account, long orderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", account);
        params.put("orderId", orderId);
        MethodMessage methodMessage = new SubmitOrderMessage(params, orderService);
        queue.offer(methodMessage);
    }
}
