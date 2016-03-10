package com.github.zjiajun.java.core.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujiajun
 * 16/1/27 21:32
 */
public class CopyOnWriteMap<K,V> {

    private volatile Map<K,V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<>();
    }

    public V put(K key,V value) {
        synchronized (this) {
            Map<K,V> newMap = new HashMap<>(internalMap);
            V v = newMap.put(key, value);
            internalMap = newMap;
            return v;
        }
    }

    public V get(K key) {
        return internalMap.get(key);
    }

    public void putAll(Map<? extends K,? extends V> newData) {
        synchronized (this) {
            Map<K,V> newMap = new HashMap<>(internalMap);
            newMap.putAll(newData);
            internalMap = newMap;
        }
    }
}
