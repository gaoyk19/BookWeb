package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // a.获取请求参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
//        String email=request.getParameter("email");

        if(userService.login(new User(null,username,password,null))==null){
            System.out.println("登陆失败！");
            request.getRequestDispatcher("/pages/user/login.html").forward(request,response);
        }else{
            request.getRequestDispatcher("/pages/user/login_success.html").forward(request,response);
        }
    }
}
