package com.hussein.lock;

/**
 * <p>Title: ThreadCreationCmp</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/10 8:19 PM
 */
public class ThreadCreationCmp {

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        CountTask countTask = new CountTask();
        for (int i = 0; i < 2 * processors; i++) {
            new Thread(countTask).start();
            new CountThread().start();
        }
    }

    private static void doSomething() {
        Tools.pause();
    }

    static class Counter {

        private int count = 0;

        public void increase() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    static class CountTask implements Runnable {

        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increase();
                ThreadCreationCmp.doSomething();
            }
            System.out.println("CountTask:" + counter.getCount());
        }
    }

    static class CountThread extends Thread {

        private Counter counter = new Counter();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter.increase();
                ThreadCreationCmp.doSomething();
            }
            System.out.println("CountThread:" + counter.getCount());
        }
    }
}
