package dao.impl;

import dao.BookDao;
import pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(`name`,`price`,`author`,`sales`,`stock`,`img_path`) values (?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getStock());
    }

    @Override
    public int deleteBookById(int id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where `id`=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
//        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        String sql = "select * from book.t_book where `id` = ?";
        return queryForOne(Book.class, sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book";
        return queryForList(Book.class,sql);
    }

    //TODO 返回图书的总数量
    public int queryForPageTotalCount(){
        String sql="select count(*) from t_book";
        //TODO 为什么将返回值类型转化为Number类型？
        Number count=(Number)queryForSingleValue(sql);
        return count.intValue();
    }

    //返回当前页面的图书列表
    public List<Book>queryForItems(int begin,int pageSize){
        String sql="select `id`,`name`,`price`,`author`,`sales`,`stock`,`img_path` from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }
}
