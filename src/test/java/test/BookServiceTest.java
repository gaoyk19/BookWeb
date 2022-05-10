package test;

import junit.framework.TestCase;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;

public class BookServiceTest extends TestCase {
    BookService bookService=new BookServiceImpl();
    public void testAddBook() {
        bookService.addBook(new Book(null,"黄金时代",new BigDecimal(45),"王小波",80000,10,""));
    }

    public void testDeleteBookById() {

    }

    public void testUpdateBook() {
        bookService.updateBook(new Book(6,"西红柿炒鸡蛋",new BigDecimal(9.90),"周请吃",12,53,"static/img/default.jpg"));
    }

    public void testQueryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    public void testQueryBooks() {
    }

    public void testPage(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}