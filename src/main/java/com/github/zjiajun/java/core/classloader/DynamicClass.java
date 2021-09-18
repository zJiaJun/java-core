package com.github.zjiajun.java.core.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/9/18 17:05
 */
public class DynamicClass {

//    private static final Logger logger = LoggerFactory.getLogger(DynamicClass.class);

    static {
//        logger.info(DynamicClass.class.getCanonicalName() + " static running...");
        System.out.println(DynamicClass.class.getCanonicalName() + " static running...");
    }

    public DynamicClass() {
        System.out.println(DynamicClass.class.getCanonicalName() + " constructor running...");
    }

    public Integer execMethod(String a, int b) {
        int num = Integer.parseInt(a);
        return num + b;
    }
}
