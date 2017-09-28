package com.github.zjiajun.java.core.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author zhujiajun
 * @since 2017/9/28
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    public MyClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
