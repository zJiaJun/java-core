package com.github.zjiajun.java.core.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/9/19 09:42
 */
public class DynamicClassLoaderMain {

    private static final Logger logger = LoggerFactory.getLogger(DynamicClassLoaderMain.class);

    /**
     * jvm param: -verbose:class
     * @param args args
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        String resourcesPath = "/src/main/resources/class/";
        String addClassPath =  System.getProperty("user.dir") + resourcesPath + "A/DynamicClass.class";
        String multiplyClassPath =  System.getProperty("user.dir") + resourcesPath + "B/DynamicClass.class";
        String clazz = "com.github.zjiajun.java.core.classloader.DynamicClass";

        PathClassLoader addClassLoader = new PathClassLoader(addClassPath);
        PathClassLoader multiplyClassLoader = new PathClassLoader(multiplyClassPath);

        Class<?> addClass = addClassLoader.loadClass(clazz);
        logger.info(addClass.getClassLoader().toString());

        Class<?> multiplyClass = multiplyClassLoader.findClass(clazz);
        logger.info(multiplyClass.getClassLoader().toString());

        logger.info("addClass == multiplyClass {}", addClass == multiplyClass);

        Object addObj = addClass.newInstance();
        Method addMethod = addObj.getClass().getDeclaredMethod("execMethod", String.class, int.class);
        Object addResult = addMethod.invoke(addObj, "10", 2);
        logger.info("reflect execMethod(add) invoke result {}", addResult);

        Object multiplyObj = multiplyClass.newInstance();
        Method multiplyMethod = multiplyObj.getClass().getDeclaredMethod("execMethod", String.class, int.class);
        Object multiplyResult = multiplyMethod.invoke(multiplyObj, "10", 2);
        logger.info("reflect execMethod(multiply) invoke result {}", multiplyResult);

        addMethod = null;
        addObj = null;
        addClass = null;
        addClassLoader = null;
        System.gc();

        TimeUnit.SECONDS.sleep(2);



    }
}
