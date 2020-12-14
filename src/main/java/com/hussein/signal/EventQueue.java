package com.hussein.signal;

import com.hussein.lock.Tools;

import java.util.LinkedList;

/**
 * <p>Title: EventQueue</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 4:59 PM
 */
public class EventQueue {

    private int max;

    private final LinkedList<Event> queueList = new LinkedList<>();

    private static final int MAX_QUEUE_SIZE = 10;

    public EventQueue() {
        this(MAX_QUEUE_SIZE);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    static class Event {
    }

    public void offer(Event event) {
        synchronized (queueList) {
            if (queueList.size() >= max) {
                console("Queue is full.");
                try {
                    queueList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("a new event is submitted..");
            queueList.addLast(event);
            queueList.notify();
        }
    }

    public Event take() {
        synchronized (queueList) {
            if (queueList.isEmpty()) {
                console("Queue is empty.");
                try {
                    queueList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = queueList.removeFirst();
            queueList.notify();
            console("the event " + event + "is handled..");
            return event;
        }
    }

    private void console(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        EventQueue queue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                queue.offer(new Event());
            }
        }, "producer").start();
        new Thread(() -> {
            for (; ; ) {
                queue.take();
                Tools.pause();
            }
        }, "consumer").start();
    }
}
