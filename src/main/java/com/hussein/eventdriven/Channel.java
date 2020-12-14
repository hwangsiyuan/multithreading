package com.hussein.eventdriven;

/**
 * <p>Title: Channel</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:33 PM
 */
public interface Channel<E extends Message> {

    void dispatch(E message);
}
