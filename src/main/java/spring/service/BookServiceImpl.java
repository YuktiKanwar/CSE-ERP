package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.BookDao;
import spring.model.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	
	@Override
	@Transactional
	public void addBook(Book b) {
		// TODO Auto-generated method stub
		this.bookDao.addBook(b);
	}

	@Override
	@Transactional
	public void updateBook(Book b) {
		// TODO Auto-generated method stub
		this.bookDao.updateBook(b);
	}

	@Override
	@Transactional
	public List<Book> listBooks() {
		// TODO Auto-generated method stub
		return this.bookDao.listBooks();
	}

	@Override
	@Transactional
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return this.bookDao.getBookById(id);
	}

	@Override
	@Transactional
	public void removeBook(int id) {
		// TODO Auto-generated method stub
		this.bookDao.removeBook(id);
	}

	@Override
	@Transactional
	public List<Book> getBookByDepartmentId(int deptId) {
		// TODO Auto-generated method stub
		return this.bookDao.getBookByDepartmentId(deptId);
	}

}
