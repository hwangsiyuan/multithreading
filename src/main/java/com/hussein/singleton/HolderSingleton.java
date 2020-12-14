package com.hussein.singleton;

/**
 * <p>Title: HolderSingleton</p>
 * <p>Description: Holder方式单例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 2:52 PM
 */
public final class HolderSingleton {

    private byte[] data = new byte[1024];

    private HolderSingleton() {
        System.out.println("init...");
    }

    private static class Holder {

        private static HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(HolderSingleton.getInstance());
        System.out.println(HolderSingleton.getInstance());
    }
}
