package com.hussein.eventdriven;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>Title: UserChatThread</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 4:07 PM
 */
public class UserChatThread extends Thread {

    private final User user;

    private final AsyncEventMessageDispatcher dispatcher;

    public UserChatThread(String userName, AsyncEventMessageDispatcher dispatcher) {
        super(userName);
        this.user = new User(userName);
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            dispatcher.dispatch(new UserOnLineEvent(user));
            IntStream.range(0, 5).forEach(i -> {
                dispatcher.dispatch(new UserChatEvent(user, "hello" + i));
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            dispatcher.dispatch(new UserOffLineEvent(user));
        }
    }
}
