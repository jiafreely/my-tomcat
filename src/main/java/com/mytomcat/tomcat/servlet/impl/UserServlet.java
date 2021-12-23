package com.mytomcat.tomcat.servlet.impl;

import com.mytomcat.tomcat.http.Request;
import com.mytomcat.tomcat.http.Response;

import java.io.OutputStream;

/**
 * @author xjh
 * @version 1.0
 * @ClassName: UserServlet
 * @description: TODO
 * @date 2021/12/23 11:29
 */
public class UserServlet extends MyHttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        this.doPost(request, response);
    }

    @Override
    public void doPost(Request request, Response response) {
        try {
            //省略业务调用的代码，tomcat会根据request对象里面的inputStream拿到对应的参数进行业务调用
            //模拟业务层调用后的返回
            OutputStream outputStream = response.outputStream;
            String result = Response.responsebody + "user handle successful";
            outputStream.write(result.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
