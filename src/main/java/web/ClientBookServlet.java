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

public class ClientBookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//        System.out.println("经过了前台的clientBookServlet程序");
        //1.获取请求参数，当前的页码pageNo 以及 页面展示量pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize),获取page对象
        Page<Book>page=bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.将page信息保存到request域中
        request.setAttribute("page",page);
        //4.将请求转发到 page/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
}
