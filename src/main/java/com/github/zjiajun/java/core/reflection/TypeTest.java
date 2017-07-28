package com.github.zjiajun.java.core.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhujiajun
 * @since 2017/7/28
 */
public class TypeTest {

    public abstract class AbstractType<T> {

        protected void type() {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            System.out.println(actualTypeArguments[0]);
            System.out.println(actualTypeArguments[0].getTypeName());
        }
    }

    public class Demo extends AbstractType<Demo> {

        public Demo() {
        }

        @Override
        protected void type() {
            super.type();
        }
    }

    public static void main(String[] args) {
        Demo demo = new TypeTest().new Demo();
        demo.type();

    }
}
