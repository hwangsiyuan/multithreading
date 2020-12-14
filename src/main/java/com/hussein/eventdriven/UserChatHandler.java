package com.hussein.eventdriven;

/**
 * <p>Title: UserOnLineEvent</p>
 * <p>Description: 用户聊天处理</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:59 PM
 */
public class UserChatHandler extends AsyncChannel {

    @Override
    protected void handler(Message message) {
        UserChatEvent chatEvent = (UserChatEvent) message;
        System.out.printf("user [%s] say:[%s].\n", chatEvent.getUser().getUserName(), chatEvent.getMessage());
    }
}
