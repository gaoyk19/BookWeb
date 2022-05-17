package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import junit.framework.TestCase;
import pojo.OrderItem;

import java.math.BigDecimal;

public class OrderItemDaoTest extends TestCase {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();

    public void testSaveOrderItem() {
//        注意order_id在创建数据库表的时候，设定为外键且必须在t_order表中
        OrderItem orderItem=new OrderItem(123,"123", 40, new BigDecimal(80), new BigDecimal(80), "12345");
         orderItemDao.saveOrderItem(orderItem);
    }
}