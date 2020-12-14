package com.hussein.eventbus;

import java.lang.reflect.Method;

/**
 * <p>Title: Subscriber</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 11:27 AM
 */
public class Subscriber {

    private final Object subscriber;

    private final Method method;

    private Boolean enable = true;

    public Subscriber(Object subscriber, Method method) {
        this.subscriber = subscriber;
        this.method = method;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public Method getMethod() {
        return method;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
