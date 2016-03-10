package com.github.zjiajun.java.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhujiajun
 * 16/1/20 20:56
 */
@Api(value = "this is class annotation")
public class ApiClass {

    @Api("this is field annotation")
    private int number;

    @Api("this is method annotation")
    private void annotationMethod() {
        System.out.println("annotationMethod");
    }

    public static void main(String[] args) {
        Class<ApiClass> apiClassClass = ApiClass.class;
        System.out.println(apiClassClass.isAnnotationPresent(Api.class));
        Api annotation = apiClassClass.getAnnotation(Api.class);
        if (annotation != null)
            System.out.println(annotation.value());

        System.out.println("------");

        Method[] declaredMethods = apiClassClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Api methodAnnotation = method.getAnnotation(Api.class);
            if (methodAnnotation != null)
                System.out.println(methodAnnotation.value());
        }

        System.out.println("------");

        Field[] declaredFields = apiClassClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Api fieldAnnotation = field.getAnnotation(Api.class);
            if (fieldAnnotation != null)
                System.out.println(fieldAnnotation.value());
        }

    }
}
