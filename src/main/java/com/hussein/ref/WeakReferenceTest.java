package com.hussein.ref;

import java.lang.ref.WeakReference;

/**
 * <p>Title: WeakReferenceTest</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/9 10:28 AM
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        MyReference ref = new MyReference();
        WeakReference<MyReference> weakRef = new WeakReference<>(ref);
        ref = null;
        System.gc();
    }
}
