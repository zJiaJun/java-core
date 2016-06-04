package com.github.zjiajun.java.core.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * Created by zhujiajun
 * 16/6/4 10:43
 *
 * 使用代理ip,通过 httpbin.org/ip 进行验证
 */
public class ProxyExample {

    private void getIp() throws IOException {
        URL url = new URL("http://httpbin.org/ip");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        int bufferSize = 128;
        char [] buffer = new char[bufferSize];
        int charsRead;
        while ((charsRead = br.read(buffer,0,bufferSize)) != -1) {
            sb.append(buffer,0,charsRead);
        }
        br.close();
        System.out.println(sb.toString());
    }

    private void setProxy() {
        Properties prop = System.getProperties();
        // 设置http访问要使用的代理服务器的地址
        prop.setProperty("http.proxyHost", "122.138.87.91");
        // 设置http访问要使用的代理服务器的端口
        prop.setProperty("http.proxyPort", "8888");
        // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
        prop.setProperty("http.nonProxyHosts", "localhost|192.168.0.*");
    }


    public static void main(String[] args) throws IOException {
        ProxyExample proxyExample = new ProxyExample();
        proxyExample.getIp();
        proxyExample.setProxy();
        proxyExample.getIp();

    }
}
