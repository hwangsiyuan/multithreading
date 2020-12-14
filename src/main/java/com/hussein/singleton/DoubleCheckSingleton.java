package com.hussein.singleton;

import java.net.Socket;

/**
 * <p>Title: DoubleCheckSingleton</p>
 * <p>Description: 双重检查单例，能保证返回同一实例但可能会有socket初始化的问题</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:44 PM
 */
public final class DoubleCheckSingleton {

    private byte[] data = new byte[1024];

    private static DoubleCheckSingleton INSTANCE = null;

    private Socket socket;

    private DoubleCheckSingleton() {
        socket = new Socket();
    }

    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
