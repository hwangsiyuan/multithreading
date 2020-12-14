package com.hussein.eventbus;

/**
 * <p>Title: EventExceptionHandler</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 1:57 PM
 */
public interface EventExceptionHandler {

    void handler(Throwable e, EventContext context);
}
