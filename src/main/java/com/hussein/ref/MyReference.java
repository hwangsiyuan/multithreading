package com.hussein.ref;

/**
 * <p>Title: MyReference</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 2:08 PM
 */
public class MyReference {

    /**
     * 1M = 1024k = 1024 * 1024B=2^20
     */
    private byte[] data = new byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {
        System.out.println("the reference will be GC.");
    }
}
