package com.hussein.activeobj;

import java.util.LinkedList;

/**
 * <p>Title: ActiveMessageQueue</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 4:33 PM
 */
@SuppressWarnings("all")
public class ActiveMessageQueue {

    private final LinkedList<ActiveMessage> queue = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage activeMessage) {
        synchronized (this) {
            queue.addLast(activeMessage);
            this.notifyAll();
        }
    }

    public ActiveMessage take() {
        synchronized (this) {
            while (queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.removeFirst();
        }
    }

}
