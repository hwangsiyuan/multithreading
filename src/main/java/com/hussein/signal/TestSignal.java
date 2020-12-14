package com.hussein.signal;

/**
 * <p>Title: TestSignal</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/23 5:41 PM
 */
public class TestSignal {

    public void testWait1() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testNotify1() {
        this.notify();
    }

    private final Object MUTEX = new Object();

    public synchronized void testWait2() {
        try {
            MUTEX.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void testNotify2() {
        MUTEX.notify();
    }

    /**
     * 以下每个方法都会抛出
     * java.lang.IllegalMonitorStateException
     * 只有获取同一个对象的monitor后才能调用wait/notify
     * @param args
     */
    public static void main(String[] args) {
//        TestSignal t = new TestSignal();
//        t.testWait1();
//        t.testNotify1();
//        t.testWait2();
//        t.testWait2();
    }

}
