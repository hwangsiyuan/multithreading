package com.hussein.eventdriven;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title: AsyncEventMessageDispatcher</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 3:22 PM
 */
public class AsyncEventMessageDispatcher implements DynamicRouter<Message> {

    private final ConcurrentHashMap<Class<? extends Message>, AsyncChannel> router = new ConcurrentHashMap<>();

    @Override
    public void register(Class<? extends Message> msgClazz, Channel<? extends Message> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("The channel must be AsyncChannel type.");
        }
        router.put(msgClazz, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Message message) {
        Class<? extends Message> type = message.getType();
        if (router.containsKey(type)) {
            router.get(type).dispatch(message);
        } else {
            throw new MethodMatcherException("cannot match channel for [" + message.getType() + "] type.");
        }
    }

    public void shutdown() {
        router.values().forEach(AsyncChannel::stop);
    }
}
