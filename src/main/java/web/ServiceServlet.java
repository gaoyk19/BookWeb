package web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(name = "ServiceServlet", value = "/ServiceServlet")
public class ServiceServlet extends HttpServlet {

    /**
     * a. 获取请求参数
     * b. 检查验证码是否正确
     * c. 检查用户是否可用
     *  1.可用：调用service服务跳转到“注册成功”页面
     *  2.不可用：重新到注册页面
     * @return */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
