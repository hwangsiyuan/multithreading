package com.hussein.eventdriven;

import java.util.LinkedList;

/**
 * <p>Title: EventLoop</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:14 PM
 */
public class EventLoop {

    private final LinkedList<Event> events = new LinkedList<>();

    private final EventHandler eventHandler = new EventHandler();

    public void offerEvent(Event e) {
        events.addLast(e);
    }

    public void loop() {
        while (!events.isEmpty()) {
            Event event = events.remove();
            if (event != null) {
                if (event.getType().equals("A")) {
                    eventHandler.methodA(event);
                } else if (event.getType().equals("B")) {
                    eventHandler.methodB(event);
                }
            }
        }
    }

    public static void main(String[] args) {
        EventLoop loop = new EventLoop();
        loop.offerEvent(new Event("A","hello"));
        loop.offerEvent(new Event("A","I am event A."));
        loop.offerEvent(new Event("B","hello"));
        loop.offerEvent(new Event("B","I am event B."));
        loop.loop();
    }

}
