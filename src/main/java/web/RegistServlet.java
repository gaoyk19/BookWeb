package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(name = "ServiceServlet", value = "/ServiceServlet")
public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    /**
     * a. 获取请求参数
     * b. 检查验证码是否正确
     * c. 检查用户是否可用
     *  1.可用：调用service服务跳转到“注册成功”页面
     *  2.不可用：重新到注册页面
     * @return */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // a.获取请求参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String code=request.getParameter("code");

        //b.检查验证码是否正确(忽略大小写)
        if("abcde".equalsIgnoreCase(code)){
            if(userService.isExistUser(username)){
                //用户名已经存在 跳回注册页面
                System.out.println("用户名 ["+username+" ]已经存在！");
                request.getRequestDispatcher("/pages/user/regist.html").forward(request,response);
            }else{
                //用户名不存在,可以注册,然后跳转到注册成功页面
                userService.registUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.html").forward(request,response);
            }
        }else{
            //跳回注册页面
            System.out.println("验证码 ["+code+" [ 错误！");
            request.getRequestDispatcher("/pages/user/regist.html").forward(request,response);
        }

    }
}
