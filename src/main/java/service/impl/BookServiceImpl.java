package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book>page =new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        //总记录数
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //总页码
        Integer pageTotal=pageTotalCount%pageSize==0 ? pageTotalCount/pageSize: pageTotalCount/pageSize+1;
        page.setPageTotal(pageTotal);

        //查询当前页面的图书列表
        int begin=(pageNo-1)*pageSize;
        List<Book>items=bookDao.queryForItems(begin,pageSize);
        page.setItems(items);
        return page;
    }
}
