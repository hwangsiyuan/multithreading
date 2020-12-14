package com.hussein.eventdriven;

/**
 * <p>Title: UserChatApplication</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 4:13 PM
 */
public class UserChatApplication {

    public static void main(String[] args) {
        AsyncEventMessageDispatcher dispatcher = new AsyncEventMessageDispatcher();
        dispatcher.register(UserOnLineEvent.class, new UserOnLineHandler());
        dispatcher.register(UserOffLineEvent.class, new UserOffLineHandler());
        dispatcher.register(UserChatEvent.class, new UserChatHandler());
        new UserChatThread("LanYi", dispatcher).start();
        new UserChatThread("Jessi", dispatcher).start();
        new UserChatThread("Mike", dispatcher).start();
    }
}
