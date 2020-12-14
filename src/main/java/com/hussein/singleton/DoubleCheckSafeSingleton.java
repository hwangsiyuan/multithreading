package com.hussein.singleton;

import java.net.Socket;

/**
 * <p>Title: DoubleCheckSafeSingleton</p>
 * <p>Description: 双重检查单例，能保证返回同一实例并且保证初始化安全/p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:44 PM
 */
public final class DoubleCheckSafeSingleton {

    private byte[] data = new byte[1024];

    private static volatile DoubleCheckSafeSingleton INSTANCE = null;

    private Socket socket;

    private DoubleCheckSafeSingleton() {
        socket = new Socket();
    }

    public static DoubleCheckSafeSingleton getInstance() {
        if(INSTANCE == null){
            synchronized (DoubleCheckSafeSingleton.class){
                if(INSTANCE == null){
                    INSTANCE = new DoubleCheckSafeSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
