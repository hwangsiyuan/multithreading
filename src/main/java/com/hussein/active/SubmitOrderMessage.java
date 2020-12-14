package com.hussein.active;

import java.util.Map;

/**
 * <p>Title: SubmitOrderMessage</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 5:22 PM
 */
public class SubmitOrderMessage extends MethodMessage {

    public SubmitOrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");
        orderService.submitOrder(account, orderId);
    }
}
