package com.hussein.singleton;

/**
 * <p>Title: LazySingleton</p>
 * <p>Description: 懒汉式单例，多线程下可能会返回多个实例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:37 PM
 */
public final class LazySingleton {

    private byte[] data = new byte[1024];

    private static LazySingleton INSTANCE = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}
