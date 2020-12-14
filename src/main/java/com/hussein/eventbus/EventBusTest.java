package com.hussein.eventbus;

/**
 * <p>Title: EventBusTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/13 2:54 PM
 */
public class EventBusTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleSubscriber1());
        eventBus.register(new SimpleSubscriber2());
        eventBus.post("hello");
        System.out.println("-----------------------");
        eventBus.post("hello", "test");
    }
}
