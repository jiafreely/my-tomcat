package com.mytomcat.tomcat.http;

import java.io.OutputStream;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: Response
 * @description: TODO
 * @date 2021/12/23 11:12
 */
public class Response {
    public OutputStream outputStream;

    public static final String responsebody = "HTTP/1.1 200+\r\n" + "Content-Type：text/html+\r\n"
            + "\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
