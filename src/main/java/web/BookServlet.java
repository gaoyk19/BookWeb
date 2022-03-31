package web;

import pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    
    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
