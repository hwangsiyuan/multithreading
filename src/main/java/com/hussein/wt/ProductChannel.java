package com.hussein.wt;

/**
 * <p>Title: ProductChannel</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 11:29 AM
 */
public class ProductChannel {

    private final static int MAX_PRODUCT_COUNT = 100;

    private final Production[] productionQueue;

    private int head;

    private int tail;

    private int total;

    private final Worker[] workers;

    public ProductChannel(int workerSize) {
        productionQueue = new Production[MAX_PRODUCT_COUNT];
        workers = new Worker[workerSize];
        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("worker-" + i, this);
            workers[i].start();
        }
    }

    public void offer(Production production) throws InterruptedException {
        synchronized (this) {
            while (total > productionQueue.length) {
                this.wait();
            }
            productionQueue[tail] = production;
            tail = (tail + 1) % productionQueue.length;
            total++;
            this.notifyAll();
        }
    }

    public Production get() throws InterruptedException {
        synchronized (this) {
            while (total <= 0) {
                this.wait();
            }
            Production production = productionQueue[head];
            head = (head + 1) % productionQueue.length;
            total--;
            this.notifyAll();
            return production;
        }
    }
}
