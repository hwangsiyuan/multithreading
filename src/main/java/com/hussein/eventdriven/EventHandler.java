package com.hussein.eventdriven;

/**
 * <p>Title: EventHandler</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/14 2:12 PM
 */
public class EventHandler {

    public void methodA(Event event) {
        System.out.println(event.getData().toUpperCase());
    }

    public void methodB(Event event) {
        System.out.println(event.getData().toLowerCase());
    }
}
