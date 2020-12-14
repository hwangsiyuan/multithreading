package com.hussein.singleton;

/**
 * <p>Title: HungryEnumSingleton</p>
 * <p>Description: 饿汉式枚举单例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:59 PM
 */
public enum HungryEnumSingleton {

    INSTANCE;

    private byte[] data = new byte[1024];

    HungryEnumSingleton() {
        System.out.println("init.");
    }

    public static void method(){

    }

    public static HungryEnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(HungryEnumSingleton.getInstance());
        System.out.println(HungryEnumSingleton.getInstance());
        System.out.println(HungryEnumSingleton.getInstance());
    }
}
