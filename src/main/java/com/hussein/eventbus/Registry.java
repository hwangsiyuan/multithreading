package com.hussein.eventbus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * <p>Title: Registry</p>
 * <p>Description: 注册中心</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 11:29 AM
 */
class Registry {

    private final ConcurrentHashMap<String, ConcurrentLinkedDeque<Subscriber>> subscriberContainer = new ConcurrentHashMap<>();

    public void register(Object subscriber) {
        //获取所有@Subscribe注解的方法包含父类的相关方法
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    public void unregister(Object subscriber) {
        subscriberContainer.forEach((topic, subscribers) -> {
            subscribers.forEach(s -> {
                if (s.getSubscriber() == subscriber) {
                    s.setEnable(false);
                }
            });
        });
    }

    public ConcurrentLinkedDeque<Subscriber> scanSubscriber(String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        Subscribe subscribe = method.getAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedDeque<>());
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> subscribeMethods = new ArrayList<>();
        Class<?> clazz = subscriber.getClass();
        while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            Arrays.stream(methods)
                    .filter(m ->
                            m.isAnnotationPresent(Subscribe.class) && m.getModifiers() == Modifier.PUBLIC && m.getParameterCount() == 1)
                    .forEach(subscribeMethods::add);
            clazz = clazz.getSuperclass();
        }
        return subscribeMethods;
    }
}
