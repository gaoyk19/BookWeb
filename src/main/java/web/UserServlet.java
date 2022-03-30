package web;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //用户名不存在,可以注册,然后跳转到注册成功页面
                userService.registUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else{
            //跳回注册页面
            request.setAttribute("msg","验证码错误！");
            request.setAttribute("username",username);
            request.setAttribute("email",email);

            System.out.println("验证码 ["+code+" [ 错误！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if("login".equals(request.getParameter("action"))){
//            login(request,response);
//        }else{
//            regist(request,response);
//        }
        //使用反射优化if-else代码
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            Method method=this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
