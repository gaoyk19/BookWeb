package test;

import junit.framework.TestCase;
import pojo.Book;
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
    }

    public void testQueryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    public void testQueryBooks() {
    }
}