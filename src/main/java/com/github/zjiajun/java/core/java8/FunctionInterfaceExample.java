package com.github.zjiajun.java.core.java8;

/**
 * Created by zhujiajun
 * 16/7/1 13:50
 */
public class FunctionInterfaceExample {

    @FunctionalInterface
    private interface FunctionListener {

        void handlerResponse(Object response);

        default void onSuccess(String url,Object response) {
            System.out.println(url + " 下载成功");
            handlerResponse(response);
        }

        default void onFailure(String url,Object response,Exception e) {
            System.out.println(url + " 下载失败 cause : " + e.toString());
            handlerResponse(response);
        }
    }

    public void doDownload(String url,FunctionListener listener) {
        //模拟下载方法,成功或失败回调
        try {
            System.out.println("Begin download " + url.toUpperCase());
            if (listener != null) listener.onSuccess(url, new Object());
        } catch (Exception e) {
            if (listener != null) listener.onFailure(url, new Object(), e);
        }
    }

    public static void main(String[] args) {
        FunctionInterfaceExample example = new FunctionInterfaceExample();

        example.doDownload("http://www.example.com",response -> System.out.println("处理正常返回" + response));
        example.doDownload(null, response -> System.out.println("处理出错返回"));

    }

}
