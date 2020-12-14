package com.hussein.active;

import com.hussein.future.Future;

import java.util.Map;

/**
 * <p>Title: GetOrderDetailMessage</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 5:15 PM
 */
public class GetOrderDetailMessage extends MethodMessage {

    public GetOrderDetailMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture = orderService.getOrderById((Long) params.get("orderId"));
        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");
        try {
            String result = realFuture.get();
            activeFuture.finish(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            activeFuture.finish(null);
        }
    }
}
