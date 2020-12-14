package com.hussein.activeobj;

/**
 * <p>Title: ActiveDaemonThread</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/12 4:48 PM
 */
@SuppressWarnings("all")
public class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            ActiveMessage activeMessage = queue.take();
            activeMessage.execute();
        }
    }
}
