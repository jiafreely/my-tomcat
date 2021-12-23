package com.mytomcat.tomcat.handler;

import com.mytomcat.tomcat.http.Request;
import com.mytomcat.tomcat.http.Response;
import com.mytomcat.tomcat.servlet.impl.MyHttpServlet;
import com.mytomcat.tomcat.socket.MyTomcat;

import java.io.*;
import java.net.Socket;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: RequestHandler
 * @description: 解决服务器一次只能连接一个客户端。tomcat在解决这个问题时使用了BIO模型，简单来讲就是每个连接一个线程，下面就来实现BIO
 * @date 2021/12/23 10:59
 * <p>
 * 流程:服务端获取inputStream流使用Request解析流的URL和method,用Respose封装返回给客户端的输出流
 * 然后根据url找到对应的servletname,如果servletname不为空则为web.xml中的映射,(urlmapping和servletMapping为web.xml中的内容,init()初始化的时候解析web.xml中的内容)
 * 不为空则执行service()方法业务处理,为空则返回can not find servlet
 */
public class RequestHandler implements Runnable {
    public Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    //继承Runnable接口，实现run方法
    public void run() {
        InputStream inputStream = null;
        try {
            //将inputstream封装成我们自己的request，用来获取uri，method等信息
            Request request = new Request(socket.getInputStream());
            //将outputstream封装成我们的response对象
            Response response = new Response(socket.getOutputStream());

            System.out.println("执行客户请求:" + Thread.currentThread());
            String uri = request.getUri();
            System.out.println(uri);
            //根据uri得到servletname
            String servletname = MyTomcat.urlmapping.get(uri);
            //根据servletname得到Servlet对象，如果web.xml文件中有映射就不为空
            MyHttpServlet servlet = MyTomcat.servletMapping.get(servletname);

            if (servlet != null) {
                //不为空执行service方法，即跳转到doGet和doPost方法
                servlet.service(request, response);
            } else {
                String resp = Response.responsebody + "can not find servlet";
                OutputStream outputStream = socket.getOutputStream();
                System.out.println(resp);
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
