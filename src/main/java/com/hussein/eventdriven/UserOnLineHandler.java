package com.hussein.eventdriven;

/**
 * <p>Title: UserOnLineEvent</p>
 * <p>Description: 用户上线处理</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:59 PM
 */
public class UserOnLineHandler extends AsyncChannel {

    @Override
    protected void handler(Message message) {
        UserOnLineEvent onLineEvent = (UserOnLineEvent) message;
        System.out.printf("user [%s] is online.\n", onLineEvent.getUser().getUserName());
    }
}
