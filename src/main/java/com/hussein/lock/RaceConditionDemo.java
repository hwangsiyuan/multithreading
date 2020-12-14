package com.hussein.lock;

/**
 * <p>Title: RaceConditionDemo</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/11 7:28 PM
 */
public class RaceConditionDemo {

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Thread[] workerThreads = new WorkerThread[availableProcessors];
        for (int i = 0; i < workerThreads.length; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }
        for (Thread t : workerThreads) {
            t.start();
        }
    }

    private static class WorkerThread extends Thread {

        private final int requestCount;

        WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < requestCount; i++) {
                RequestIdGenerator idGenerator = RequestIdGenerator.getInstance();
                String requestId = idGenerator.nextId();
                Tools.pause();
                System.out.printf("%s got requestId %s.\n", Thread.currentThread().getName(), requestId);
            }
        }
    }
}
