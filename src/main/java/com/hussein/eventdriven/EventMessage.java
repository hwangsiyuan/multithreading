package com.hussein.eventdriven;

/**
 * <p>Title: EventMessage</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:40 PM
 */
public class EventMessage implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
