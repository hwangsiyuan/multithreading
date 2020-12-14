package com.hussein.singleton;

/**
 * <p>Title: LazySyncSingleton</p>
 * <p>Description: 懒汉式加锁单例，多线程也保证返回同一个实例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:40 PM
 */
public final class LazySyncSingleton {

    private byte[] data = new byte[1024];

    private static LazySyncSingleton INSTANCE = null;

    private LazySyncSingleton() {
    }

    public static synchronized LazySyncSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySyncSingleton();
        }
        return INSTANCE;
    }
}
