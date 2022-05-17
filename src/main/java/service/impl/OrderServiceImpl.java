package service.impl;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao =new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号使用时间戳和userID保证唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        Order order=new Order(orderId,new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        //遍历购物车中的items并作为订单项保存到数据库中
        for(CartItem cartItem:cart.getItems().values()){
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新图书商品的销量和库存
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
        }
        cart.clear();
        return orderId;

    }
}
