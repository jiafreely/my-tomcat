package com.mytomcat.tomcat.servlet;

import com.mytomcat.tomcat.http.Request;
import com.mytomcat.tomcat.http.Response;

public interface MyServlet {
    void init() throws Exception;
    void service(Request request, Response response) throws Exception;
    void destory();
}
