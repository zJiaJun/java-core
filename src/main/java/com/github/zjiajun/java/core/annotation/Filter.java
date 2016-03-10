package com.github.zjiajun.java.core.annotation;

import java.lang.annotation.*;

/**
 * Created by zhujiajun
 * 16/1/21 16:39
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Filters.class)
public @interface Filter {

    String value() default "";
}
