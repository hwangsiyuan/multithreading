package com.hussein.rwlock;

/**
 * <p>Title: ReadWriteLockTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/2 4:53 PM
 */
public class ReadWriteLockTest {

    private final static String text = "Thisisaexampleofreadwritelock";

    public static void main(String[] args) {
        ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < text.length(); j++) {
                    try {
                        shareData.write(text.charAt(j));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "write-" + i).start();

        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        char[] chars = shareData.read();
                        System.out.println(Thread.currentThread().getName() + " read " + new String(chars));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "read-" + i).start();

        }
    }
}
