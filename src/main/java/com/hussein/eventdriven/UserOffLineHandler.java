package com.hussein.eventdriven;

/**
 * <p>Title: UserOnLineEvent</p>
 * <p>Description: 用户下线处理</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:59 PM
 */
public class UserOffLineHandler extends AsyncChannel {

    @Override
    protected void handler(Message message) {
        UserOffLineEvent offLineEvent = (UserOffLineEvent) message;
        System.out.printf("user [%s] is offline.\n", offLineEvent.getUser().getUserName());
    }
}
