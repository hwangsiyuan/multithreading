package com.hussein.activeobj;

import com.hussein.active.ActiveFuture;
import com.hussein.future.Future;

import java.lang.reflect.Method;

/**
 * <p>Title: ActiveMessage</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 6:06 PM
 */
class ActiveMessage {

    private final Object service;

    private final Method method;

    private final Object[] objects;

    private final ActiveFuture<Object> future;


    public ActiveMessage(ActiveMessageBuilder builder) {
        this.service = builder.service;
        this.method = builder.method;
        this.objects = builder.objects;
        this.future = builder.future;
    }

    public void execute() {
        try {
            if (future != null) {
                Object result = method.invoke(service, objects);
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                future.finish(realResult);
            }
        } catch (Exception e) {
            future.finish(null);
        }
    }

    static class ActiveMessageBuilder {

        private Object service;

        private Method method;

        private Object[] objects;

        private ActiveFuture<Object> future;

        public ActiveMessageBuilder forService(Object service) {
            this.service = service;
            return this;
        }

        public ActiveMessageBuilder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public ActiveMessageBuilder withObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public ActiveMessageBuilder returnFuture(ActiveFuture<Object> future) {
            this.future = future;
            return this;
        }

        public ActiveMessage build() {
            return new ActiveMessage(this);
        }
    }
}
