package com.hussein.singleton;

/**
 * <p>Title: HungrySingleton</p>
 * <p>Description: 饿汉式单例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:32 PM
 */
public final class HungrySingleton {

    private byte[] data = new byte[1024];

    private static HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }
}
