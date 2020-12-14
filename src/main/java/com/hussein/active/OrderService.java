package com.hussein.active;

import com.hussein.future.Future;

/**
 * <p>Title: OrderService</p>
 * <p>Description: 订单服务</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 3:23 PM
 */
public interface OrderService {

    /**
     * 查询订单
     */
    Future<String> getOrderById(long orderId);

    /**
     * 提交订单
     */
    void submitOrder(String account, long orderId);
}
