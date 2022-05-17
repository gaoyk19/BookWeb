package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import junit.framework.TestCase;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest extends TestCase {
    OrderDao orderDao=new OrderDaoImpl();
    public void testSaveOrder() {
        Order order=new Order("12345",new Date(),new BigDecimal(123), 0, 1);
        orderDao.saveOrder(order);

    }
}