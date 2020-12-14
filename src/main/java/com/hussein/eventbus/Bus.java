package com.hussein.eventbus;

/**
 * <p>Title: Bus</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 11:13 AM
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event,String topic);

    void close();

    String getBusName();
}
