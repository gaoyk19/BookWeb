package pojo;

import java.math.BigDecimal;
import java.util.Date;

//订单
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //0表示未发货，1表示已发货，2表示已经签收
    private Integer status=0;
    private Integer userId;

    public String getOrderId() {
        return orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    //生成订单
    public  void createOrder(){

    }
    //查看所有订单
    public void showAllOrders(){}
    //发货
    public void sendOrder(){}
    //查看订单详情
    public void showOrderDetail(){}
    //查看我的订单
    public void showMyOrders(){}
    //签收订单/确认收货
    public void receiveOrder(){}

}
