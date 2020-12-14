package com.hussein.eventdriven;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: EventMessageDispatcher</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:42 PM
 */
public class EventMessageDispatcher implements DynamicRouter<Message> {

    private final Map<Class<? extends Message>, Channel> router = new HashMap<>();

    @Override
    public void register(Class<? extends Message> msgClazz, Channel<? extends Message> channel) {
        router.put(msgClazz, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (router.containsKey(message.getType())) {
            router.get(message.getType()).dispatch(message);
        } else {
            throw new MethodMatcherException("cannot match channel for [" + message.getType() + "] type.");
        }
    }
}
