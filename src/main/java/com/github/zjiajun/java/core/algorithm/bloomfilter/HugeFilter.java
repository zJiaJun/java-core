package com.github.zjiajun.java.core.algorithm.bloomfilter;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2019/2/18 21:41
 */
public interface HugeFilter {



    /**
     * 1000
     */
    Integer SIZE_OF_1K = 1000;

    /**
     * 1kw
     */
    Integer SIZE_OF_1KW = 1000_0000;

    /**
     * 有一个非常大的数据集合，假设是int类型，给一个数，判断是否在其中
     * @param num
     * @return
     */
    boolean contains(Integer num);

}
