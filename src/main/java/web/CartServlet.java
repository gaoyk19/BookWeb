package web;

import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取商品ID
        int id= WebUtils.parseInt(request.getParameter("id"),0 );
        //2. 调用bookService方法查询得到图书信息
        Book book=bookService.queryBookById(id);
        //3. 将图书信息转化为CartItem，并调用Cart.addItem()添加商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //购物车是保存在cookie中
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //4. 重定向为商品列表页面

        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));
        response.sendRedirect(request.getHeader("Referer"));
    }


}
