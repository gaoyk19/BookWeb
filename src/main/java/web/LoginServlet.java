package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            request.setAttribute("msg","用户名或密码错误！");
            request.setAttribute("username",username);

            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{

            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}
