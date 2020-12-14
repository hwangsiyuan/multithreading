package com.hussein.eventbus;

import java.util.concurrent.Executor;

/**
 * <p>Title: EventBus</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 2:18 PM
 */
public class EventBus implements Bus {

    private final Registry registry = new Registry();

    private final String busName;

    private final Executor executor;

    private final EventExceptionHandler handler;

    private final Dispatcher dispatcher;

    private final static String DEFAULT_NAME = "default-bus";

    private final static String DEFAULT_TOPIC = "default-topic";

    public EventBus() {
        this(DEFAULT_NAME, Dispatcher.SEQ_EXECUTOR, null);
    }

    public EventBus(EventExceptionHandler handler) {
        this(DEFAULT_NAME, Dispatcher.SEQ_EXECUTOR, handler);
    }

    public EventBus(String busName, Executor executor, EventExceptionHandler handler) {
        this.busName = busName;
        this.executor = executor;
        this.handler = handler;
        this.dispatcher = Dispatcher.newDispatcher(executor, handler);
    }

    @Override
    public void register(Object subscriber) {
        registry.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        registry.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        dispatcher.dispatch(this, registry, event, topic);
    }

    @Override
    public void close() {
        dispatcher.close();
    }

    @Override
    public String getBusName() {
        return busName;
    }
}
