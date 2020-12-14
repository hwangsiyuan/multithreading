package com.hussein.eventdriven;

/**
 * <p>Title: DynamicRouter</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:36 PM
 */
public interface DynamicRouter<E extends Message> {

    void register(Class<? extends Message> message, Channel<? extends E> channel);

    void dispatch(E message);
}
