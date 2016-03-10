package com.github.zjiajun.java.core.annotation;

/**
 * Created by zhujiajun
 * 16/1/21 16:42
 */
@Filter("/admin")
@Filter("/filter")
public class FilterClass {

    public static void main(String[] args) {
        Class<FilterClass> filterClassClass = FilterClass.class;
        Filter[] annotationsByType = filterClassClass.getAnnotationsByType(Filter.class);
        if (annotationsByType != null) {
            for (Filter filter : annotationsByType) {
                System.out.println(filter.value());
            }
        }
        System.out.println(filterClassClass.getAnnotation(Filter.class));
    }
}
