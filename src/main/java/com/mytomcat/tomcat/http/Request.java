package com.mytomcat.tomcat.http;

import java.io.*;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: Request
 * @description: TODO
 * @date 2021/12/23 11:14
 */
public class Request {
    //获取uri，如 /user
    private String uri;
    //获取请求方法，这里只写get和post GET or POST
    private String method;

    public Request(InputStream inputStream) {
        try {
            //获取inputStream
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //取HTTP请求响应的第一行，GET /user HTTP/1.1，按空格隔开
            String[] data = read.readLine().split(" ");
            //取uri和method
            this.uri = data[1];
            this.method = data[0];
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
