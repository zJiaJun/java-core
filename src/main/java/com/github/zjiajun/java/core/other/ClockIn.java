package com.github.zjiajun.java.core.other;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhujiajun
 * 16/3/17 21:36
 */
public class ClockIn {

    private static String actionUrl = "https://www.walkiemate.com/hr_communication/loginServlet.do?jsessionId=53D7B6A6E12BD393ABEAB204CE613F02";

    public static void main(String[] args) throws IOException {

        URL url = new URL(actionUrl);
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","ext/html;charset=UTF-8");
        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write("username=zhujiajun@madhouse-inc.com&password=123456");
        out.flush();
        out.close();

        String line;
        StringBuilder sb = new StringBuilder();
        System.out.println(connection.getResponseCode());
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
    }

}
