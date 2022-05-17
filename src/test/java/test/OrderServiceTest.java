package test;

import junit.framework.TestCase;
import pojo.Cart;
import pojo.CartItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

public class OrderServiceTest extends TestCase {

    public void testCreateOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(10),new BigDecimal(20)));
        cart.addItem(new CartItem(1,"数据结构与算法",1,new BigDecimal(10),new BigDecimal(20)));
        cart.addItem(new CartItem(2,"视觉SLAM",1,new BigDecimal(10),new BigDecimal(20)));

        OrderService orderService=new OrderServiceImpl();
        orderService.createOrder(cart, 1);
    }
}