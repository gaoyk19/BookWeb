package pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车对象
public class Cart {
//    private Integer totalCount;
    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    //修改商品数量
    public Integer getTotalCount() {
        Integer totalCount=0;
        for(Map.Entry<Integer,CartItem>entry: items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
//        for(CartItem item:items.values()){
//            totalCount+=item.getCount();
//        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        totalPrice=new BigDecimal(0);
        for(CartItem item:items.values()){
            totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    //添加商品项
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经有该商品
        /*
        if(items.containsKey(cartItem.getId())){
            CartItem item=items.get(cartItem.getId());
            item.setCount(item.getCount()+1);
            items.put(item.getId(),item);
        }
         */
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            items.put(item.getId(),item);
        }else{
            item.setCount(item.getCount()+1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
            items.put(item.getId(),item);
        }

    }

    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    //修改某个商品数量
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.put(id,item);
        }
    }
}
