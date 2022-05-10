package web;

import pojo.Book;
import pojo.Page;
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

        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));

    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr=request.getParameter("id");
        int id = Integer.parseInt(idStr);
        bookService.deleteBookById(id);
        //request.getParameter("pageNo")从book_edit.jsp页面获取数据
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book=new Book();
//        book.setId(Integer.parseInt(request.getParameter("id")));
        WebUtils.copyParamToBean(request.getParameterMap(),book); //可以将book_edit.jsp页面中“hidden”域中的数据提取出来
        bookService.updateBook(book);
        //重定向到图书列表管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
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

    //TODO 我理解为如果调用该page方法就不会调用 上面的list方法，见manager_menu.jsp页面配置
    //图书分页
    protected void page(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //1.获取请求参数，当前的页码pageNo 以及 页面展示量pageSize
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize),获取page对象
        Page<Book>page=bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet");
        //3.将page信息保存到request域中
        request.setAttribute("page",page);
        //4.将请求转发到 page/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

}
