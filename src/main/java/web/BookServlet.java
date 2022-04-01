package web;

import pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.获取请求参数，封装得到Book对象
        Book book=new Book();
        WebUtils.copyParamToBean(request.getParameterMap(),book);
        //2. 将图书信息保存到数据库中
        bookService.addBook(book);
        //3. 跳转到图书列表页面
        //TODO 注意这里如果使用“转发”的话，是不合理的，每次按下F5就会调用addBook方法,下面使用重定向解决这个问题
//        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);

        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");


    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr=request.getParameter("id");
        int id = Integer.parseInt(idStr);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //返回图书列表
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1. 通过service查询得到图书信息
        List<Book> books = bookService.queryBooks();
        //2. 将图书信息保存到request域中
        request.setAttribute("books",books);
        //3. 将请求转发到 book_manager.jsp中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

}
