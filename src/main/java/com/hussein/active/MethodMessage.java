package com.hussein.active;

import java.util.Map;

/**
 * <p>Title: MethodMessage</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 4:31 PM
 */
public abstract class MethodMessage {

    protected final Map<String,Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
