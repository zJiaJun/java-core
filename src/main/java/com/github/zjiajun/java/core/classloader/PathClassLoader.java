package com.github.zjiajun.java.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author zhujiajun
 * @since 2017/9/30
 */
public class PathClassLoader extends ClassLoader {

    private final String path;

    public PathClassLoader(String path) {
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte [] classData = getData(name);
        if (null == classData) {
            throw new ClassNotFoundException();
        } else {
            return super.defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String name) {
        String p = path + File.separatorChar + name.replace('.', File.separatorChar) + ".class";
        try {
            InputStream is = new FileInputStream(p);
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

    public static void main(String[] args) throws ClassNotFoundException {
        PathClassLoader pathClassLoader = new PathClassLoader("/Users/zhujiajun/Work/temp");
        Class<?> aClass = pathClassLoader.findClass("com.github.zjiajun.java.core.classloader.ClassLoaderInheritance");
        System.out.println(aClass);
        ClassLoader classLoader = aClass.getClassLoader();
        while (null != classLoader) {
            System.out.println(classLoader.getClass().getCanonicalName());
            classLoader = classLoader.getParent();
        }
    }
}
