package com.hussein.ref;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>Title: SoftRefLRUCache</p>
 * <p>Description: least recently used</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/8/8 2:17 PM
 */
public class SoftRefLRUCache<K, V> {

    /**
     * 缓存key
     */
    private final LinkedList<K> keyList = new LinkedList<>();

    /**
     * 缓存数据
     */
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * 最大容量
     */
    private int capacity;

    /**
     * 缓存加载
     */
    private CacheLoader cacheLoader;

    public SoftRefLRUCache(int capacity, CacheLoader cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K k, V v) {
        if (keyList.size() >= capacity) {
            K oldestKey = keyList.removeFirst();
            cache.remove(oldestKey);
        }
        if (keyList.contains(k)) {
            keyList.remove(k);
        }
        keyList.addLast(k);
        cache.put(k, new SoftReference(v));
    }

    public V get(K k) {
        V v;
        boolean success = keyList.remove(k);
        if (!success) {
            v = (V) cacheLoader.load(k);
            this.put(k, v);
        } else {
            v = cache.get(k).get();
            keyList.addLast(k);
        }
        return v;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }

    public static void main(String[] args) {
        SoftRefLRUCache<String, MyReference> cache = new SoftRefLRUCache<>(3, k -> new MyReference());
        cache.get("Alex");
        cache.get("Jack");
        cache.get("LanYi");
        cache.get("Mike");
        System.out.println(cache);
    }
}
