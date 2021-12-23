package com.mytomcat.tomcat;

import com.mytomcat.tomcat.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: TomcatServerV1Test
 * @description: 客户端和服务器的通信
 * @date 2021/12/23 10:29
 */
public class TomcatServerV1Test {
    public static void main(String[] args) throws IOException {
        //开启ServerSocket服务，设置端口号为8080
        ServerSocket serverSocket = new ServerSocket(8080);
        //当服务没有关闭时
        System.out.println("======服务启动成功========");
        while (!serverSocket.isClosed()) {
            //使用socket进行通信
            Socket socket = serverSocket.accept();
            //对于每个连接，都开启一个线程
            RequestHandler requestHandler = new RequestHandler(socket);
            new Thread(requestHandler).start();
        }
    }

}
