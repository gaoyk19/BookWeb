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

        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));//获取地址栏中的url请求地址
        response.sendRedirect(request.getHeader("Referer"));
        //将最后添加进购物车的书籍名保存在session域中
        request.getSession().setAttribute("lastName",cartItem.getName());
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("删除购物车中图书");
        //1. 获取商品ID
        int id= WebUtils.parseInt(request.getParameter("id"),0 );
        //2. 将购物车中该物品数量减去1
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }
        //注意这里获取得到的url地址: http://localhost:8080/book/pages/cart/cart.jsp
        System.out.println("请求头Referer的值：" + request.getHeader("Referer"));//获取地址栏中的url请求地址

    }

    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取商品ID以及商品数量
        int id= WebUtils.parseInt(request.getParameter("id"),0 );
        int count=WebUtils.parseInt(request.getParameter("count"),0 );
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
}

    }
