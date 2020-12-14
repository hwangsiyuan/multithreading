package com.hussein.eventdriven;

/**
 * <p>Title: UserOnLineEvent</p>
 * <p>Description: 用户下线</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:59 PM
 */
public class UserOffLineEvent extends EventMessage {

    private final User user;

    public UserOffLineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
