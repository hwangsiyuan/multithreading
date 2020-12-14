package com.hussein.signal;

import com.hussein.lock.Tools;

import java.util.LinkedList;

/**
 * <p>Title: SecurityEventQueue</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 4:59 PM
 */
public class SecurityEventQueue {

    private int max;

    private final LinkedList<Event> queueList = new LinkedList<>();

    private static final int MAX_QUEUE_SIZE = 50;

    public SecurityEventQueue() {
        this(MAX_QUEUE_SIZE);
    }

    public SecurityEventQueue(int max) {
        this.max = max;
    }

    static class Event {
    }

    public void offer(Event event) {
        synchronized (queueList) {
            while (queueList.size() >= max) {
                console(getCurrentThreadName() + " Queue is full.");
                try {
                    queueList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(getCurrentThreadName() + " a new event is submitted..");
            queueList.addLast(event);
            queueList.notifyAll();
        }
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public Event take() {
        synchronized (queueList) {
            while (queueList.isEmpty()) {
                console(getCurrentThreadName() + " Queue is empty.");
                try {
                    queueList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = queueList.removeFirst();
            queueList.notifyAll();
            console(getCurrentThreadName() + " the event " + event + " is handled..");
            return event;
        }
    }

    private void console(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        SecurityEventQueue queue = new SecurityEventQueue();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    queue.offer(new Event());
                }
            }, "producer-" + i).start();
            new Thread(() -> {
                for (; ; ) {
                    queue.take();
                    Tools.pause();
                }
            }, "consumer-" + i).start();
        }
    }
}
