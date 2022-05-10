package dao;

import pojo.Book;

import java.util.List;

public interface BookDao {
    //增删改查
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book>queryBooks();
    public int queryForPageTotalCount();
    public List<Book>queryForItems(int begin,int pageSize);
}
