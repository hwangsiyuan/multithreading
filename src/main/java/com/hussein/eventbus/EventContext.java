package com.hussein.eventbus;

import java.lang.reflect.Method;

/**
 * <p>Title: EventContext</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 1:58 PM
 */
public interface EventContext {

    Object getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();
}
