package com.github.zjiajun.java.core.algorithm.consistent;


import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zhujiajun
 * 15/12/29 20:20
 *
 * 一致性hash算法实现
 */
public class ConsistentHash<T extends Node> {

    private HashFunction hashFunction;
    private int numberOfReplicas; //真实的机器节点对应的虚节点数量
    private final TreeMap<Integer,T> circle = new TreeMap<>(); //环形结构

    /**
     *
     * @param nodes 真实节点集合
     */
    public ConsistentHash(Collection<T> nodes) {
        this(new MurMurHashFunction(),100,nodes);
    }

    /**
     *
     * @param hashFunction      hash算法实现
     * @param numberOfReplicas  虚节点数量
     * @param nodes             真实节点集合
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        init(nodes);
    }

    /**
     * 真实节点对于虚节点
     * @param nodes 真实节点集合
     */
    private void init(Collection<T> nodes) {
        for (T node : nodes) add(node);
    }
    /**
     * 增加真实节点
     * 环形结构存放真实节点对应的虚节点
     * @param node 真实节点
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            circle.put(hashFunction.hash(node.toString() + i),node);
    }

    /**
     * 移除真实节点
     * @param node 真实节点
     */
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++)
            circle.remove(hashFunction.hash(node.toString() + i));
    }

    /**
     * 获取真实的节点
     * @param key key
     * @return Node
     */
    public T get(String key) {
        if (circle.isEmpty()) return null;
        Integer hash = hashFunction.hash(key);
        //环形结构中不包含hash
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);//顺时针找虚节点
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);//返回虚节点对应的真实节点信息
    }
}
