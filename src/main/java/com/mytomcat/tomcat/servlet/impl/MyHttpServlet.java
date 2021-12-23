package com.mytomcat.tomcat.servlet.impl;

import com.mytomcat.tomcat.http.Request;
import com.mytomcat.tomcat.http.Response;
import com.mytomcat.tomcat.servlet.MyServlet;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: MyHttpServlet
 * @description: TODO
 * @date 2021/12/23 11:25
 */
public abstract class MyHttpServlet implements MyServlet {
    public void init() throws Exception {

    }

    //如果有请求过来，就会调用这个方法，然后再根据请求类型来调用不同的doXXX（）方法
    public void service(Request request, Response response) throws Exception {
        if ("get".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }

    public void destory() {

    }

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request, Response response);
}
