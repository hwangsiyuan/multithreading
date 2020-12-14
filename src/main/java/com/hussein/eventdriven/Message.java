package com.hussein.eventdriven;

/**
 * <p>Title: Message</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:29 PM
 */
public interface Message {

    Class<? extends Message> getType();
}
