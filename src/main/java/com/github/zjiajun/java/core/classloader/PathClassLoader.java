package com.github.zjiajun.java.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhujiajun
 * @since 2017/9/30
 */
public class PathClassLoader extends ClassLoader {

    private final String clazzPath;

    public PathClassLoader(String clazzPath) {
        this.clazzPath = clazzPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name != null && !name.startsWith("com.github.zjiajun")) {
            return super.loadClass(name);
        }
        byte [] classData = getData();
        if (null == classData) {
            throw new ClassNotFoundException();
        } else {
            return super.defineClass(name, classData, 0, classData.length);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte [] classData = getData();
        if (null == classData) {
            throw new ClassNotFoundException();
        } else {
            return super.defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData() {
        try {
            InputStream is = new FileInputStream(clazzPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte [] buffer = new byte[1024 * 2];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer,0, len);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            //ignore
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        String clazzPath = System.getProperty("user.dir") + "/target/classes/com/github/zjiajun/java/core/classloader/DynamicClass.class";
        PathClassLoader pathClassLoader = new PathClassLoader(clazzPath);
        String clazz = "com.github.zjiajun.java.core.classloader.DynamicClass";
        Class<?> aClass = pathClassLoader.findClass(clazz);
        System.out.println(aClass);
//        ClassCastException
//        DynamicClass dynamicClass = (DynamicClass) aClass.newInstance();
        Object dynamicObj = aClass.newInstance();
        Method execMethod = dynamicObj.getClass().getDeclaredMethod("execMethod", String.class, int.class);
        Object result = execMethod.invoke(dynamicObj, "10", 1);
        System.out.println(result);
    }
}
