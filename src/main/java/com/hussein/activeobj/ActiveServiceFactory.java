package com.hussein.activeobj;

import com.hussein.active.ActiveFuture;
import com.hussein.future.Future;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;

/**
 * <p>Title: ActiveServiceFactory</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 7:40 PM
 */
public class ActiveServiceFactory {

    private static final LinkedList<ActiveMessage> QUEUE = new LinkedList<>();

    public static <T> T active(T instance) {
        return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), new ActiveInvocationHandler(instance));
    }

    static class ActiveInvocationHandler<T> implements InvocationHandler {

        private T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActiveMessage.ActiveMessageBuilder builder = new ActiveMessage.ActiveMessageBuilder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result = null;
                if (method.getReturnType().isAssignableFrom(Future.class)) {
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                QUEUE.offer(builder.build());
                return result;
            } else {
                return method.invoke(instance, args);
            }
        }

        private void checkMethod(Method method) throws IllegalActiveMethodException {
            if (!method.getReturnType().equals(Void.class) && !method.getReturnType().isAssignableFrom(Future.class)) {
                throw new IllegalActiveMethodException("the method [" + method.getName() + "] return type must be void/future");
            }
        }
    }
}
