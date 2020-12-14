package com.hussein.observe;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: ObservableThreadTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/1 11:35 AM
 */
@SuppressWarnings("all")
public class ObservableThreadTest {

    public static void main(String[] args) {
        TaskLifeCycle<String> lifeCycle = new TaskLifeCycle.EmptyTaskLifeCycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };
        ObservableThread thread = new ObservableThread(lifeCycle, new Task<String>() {
            @Override
            public String call() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Hello observable";
            }
        });
        thread.start();
    }
}
