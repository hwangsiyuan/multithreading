package com.hussein.singleton;

/**
 * <p>Title: LazyEnumSingleton</p>
 * <p>Description: 懒汉式枚举单例</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/7/31 3:06 PM
 */
public class LazyEnumSingleton {

    private byte[] data = new byte[1024];

    private LazyEnumSingleton() {

    }

    enum LazyEnumHolder {

        INSTANCE;

        private LazyEnumSingleton singleton;

        LazyEnumHolder() {
            singleton = new LazyEnumSingleton();
        }

        public LazyEnumSingleton getSingleton() {
            return singleton;
        }
    }

    public static LazyEnumSingleton getInstance(){
        return  LazyEnumHolder.INSTANCE.getSingleton();
    }

    public static void main(String[] args) {
        System.out.println(LazyEnumSingleton.getInstance());
        System.out.println(LazyEnumSingleton.getInstance());
        System.out.println(LazyEnumSingleton.getInstance());
    }

}
