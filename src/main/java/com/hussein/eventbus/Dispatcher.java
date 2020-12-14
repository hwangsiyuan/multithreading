package com.hussein.eventbus;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * <p>Title: Dispatcher</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 2:00 PM
 */
public class Dispatcher {

    private final Executor executor;

    private final EventExceptionHandler exceptionHandler;

    public final static Executor SEQ_EXECUTOR = SeqExecutorService.INSTANCE;

    public final static Executor PER_THREAD_EXECUTOR = PerThreadExecutorService.INSTANCE;

    private Dispatcher(Executor executor, EventExceptionHandler exceptionHandler) {
        this.executor = executor;
        this.exceptionHandler = exceptionHandler;
    }

    public static Dispatcher newDispatcher(Executor executor, EventExceptionHandler exceptionHandler) {
        return new Dispatcher(executor, exceptionHandler);
    }

    public static Dispatcher seqDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(SEQ_EXECUTOR, exceptionHandler);
    }

    public static Dispatcher perThreadDispatcher(EventExceptionHandler exceptionHandler) {
        return new Dispatcher(PER_THREAD_EXECUTOR, exceptionHandler);
    }

    public void dispatch(Bus bus, Registry registry, Object event, String topic) {
        ConcurrentLinkedDeque<Subscriber> subscribers = registry.scanSubscriber(topic);
        if (subscribers == null) {
            if (exceptionHandler != null) {
                exceptionHandler.handler(new IllegalArgumentException("the topic " + topic + "not bind yet"), new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }
        subscribers.stream()
                .filter(Subscriber::getEnable)
                .filter(subscriber -> {
                    Method method = subscriber.getMethod();
                    return method.getParameterTypes()[0].isAssignableFrom(event.getClass());
                }).forEach(subscriber -> realInvoke(subscriber, bus, event));
    }

    private void realInvoke(Subscriber subscriber, Bus bus, Object event) {
        executor.execute(() -> {
            Method method = subscriber.getMethod();
            Object object = subscriber.getSubscriber();
            try {
                method.invoke(object, event);
            } catch (Throwable e) {
                if (exceptionHandler != null) {
                    exceptionHandler.handler(e, new BaseEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    public void close() {
        if (executor instanceof ExecutorService) {
            ((ExecutorService) executor).shutdown();
        }
    }

    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PerThreadExecutorService implements Executor {

        private static final PerThreadExecutorService INSTANCE = new PerThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseEventContext implements EventContext {

        private final String source;

        private final Subscriber subscriber;

        private final Object event;

        public BaseEventContext(String source, Subscriber subscriber, Object event) {
            this.source = source;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public Object getSource() {
            return source;
        }

        @Override
        public Object getSubscriber() {
            return subscriber == null ? null : subscriber.getSubscriber();
        }

        @Override
        public Method getSubscribe() {
            return subscriber == null ? null : subscriber.getMethod();
        }

        @Override
        public Object getEvent() {
            return event;
        }
    }
}
