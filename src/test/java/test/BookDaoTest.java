package test;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import junit.framework.TestCase;
import pojo.Book;

import java.math.BigDecimal;

public class BookDaoTest extends TestCase {
    private BookDao bookDao=new BookDaoImpl();

    public void testAddBook() {
        bookDao.addBook(new Book(null,"十万个为什么",new BigDecimal(100),"gaoyk",100,100,"static/img/shiwange.jpg"));

    }

    public void testDeleteBookById() {
    }

    public void testUpdateBook() {
        bookDao.updateBook(new Book(21,"十万个为什么",new BigDecimal(100),"gaoyk",10000,300,"static/img/shiwange.jpg"));
    }

    // TODO 为什么这里查询总是报错！！！
    //因为需要在Book类中添加有参以及无参的构造函数，以及get和set方法
    public void testQueryBookById() {
//        Book book=bookDao.queryBookById(5);
        System.out.println(bookDao.queryBookById(5));
    }



    public void testQueryBooks() {
        bookDao.queryBooks();
    }
}