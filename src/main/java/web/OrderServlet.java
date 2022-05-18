package web;

import pojo.Cart;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Cart cart = (Cart)request.getSession().getAttribute("cart");
        User user=(User)request.getSession().getAttribute("user");
        //如果用户没有登陆，那么跳转到登陆页面
        if(user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        Integer userId=user.getId();
        String orderId= orderService.createOrder(cart, userId);

//        request.setAttribute("orderId",orderId);
        //会出现重复提交表单的问题
        //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);
        request.getSession().setAttribute("OrderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
