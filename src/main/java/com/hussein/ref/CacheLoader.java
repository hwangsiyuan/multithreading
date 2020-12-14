package com.hussein.ref;

/**
 * <p>Title: CacheLoader</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 2:22 PM
 */
@FunctionalInterface
public interface CacheLoader<K, V> {

    V load(K k);
}
