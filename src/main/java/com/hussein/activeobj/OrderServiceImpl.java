package com.hussein.activeobj;

import com.hussein.active.OrderService;
import com.hussein.future.Future;
import com.hussein.future.FutureService;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: 订单服务实现</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 3:26 PM
 */
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {

    @ActiveMethod
    @Override
    public Future<String> getOrderById(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderId:" + input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "the order detail info";
        }, orderId, null);
    }

    @ActiveMethod
    @Override
    public void submitOrder(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account:" + account + ",orderId:" + orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
