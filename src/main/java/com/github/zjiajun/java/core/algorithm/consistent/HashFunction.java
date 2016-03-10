package com.github.zjiajun.java.core.algorithm.consistent;

/**
 * Created by zhujiajun
 * 15/12/29 20:36
 *
 * 一致性hash算法-hash算法接口
 */
public interface HashFunction {

    Integer hash(String key);

}
