package com.hussein.eventdriven;

/**
 * <p>Title: UserOnLineEvent</p>
 * <p>Description: 用户下线</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:59 PM
 */
public class UserChatEvent extends EventMessage {

    private final User user;

    private final String message;

    public UserChatEvent(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
