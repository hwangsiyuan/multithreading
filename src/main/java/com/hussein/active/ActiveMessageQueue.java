package com.hussein.active;

import java.util.LinkedList;

/**
 * <p>Title: ActiveMessageQueue</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 4:33 PM
 */
public class ActiveMessageQueue {

    private final LinkedList<MethodMessage> queue = new LinkedList<>();

    public ActiveMessageQueue() {
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage) {
        synchronized (this) {
            queue.addLast(methodMessage);
            this.notifyAll();
        }
    }

    public MethodMessage take() {
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
