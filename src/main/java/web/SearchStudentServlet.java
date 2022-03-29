package web;

import pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "SearchStudentServlet", value = "/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getParameter("username");
//        1. 获取请求得到的参数
//        2. 发sql语句查询学生信息
//        3. 保存学生信息到request域中
//        4. 请求转发到showStudent.jsp页面

        //这里直接使用List数组模拟前两步
        List<User>userList=new ArrayList<>();
        for(int i=0;i<10;++i){
            int t=i+1;
            userList.add(new User(t,"user"+t,"password"+t,"email"+t+"@163.com"));
        }
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/include/ShowStudent.jsp").forward(request,response);
    }


}
