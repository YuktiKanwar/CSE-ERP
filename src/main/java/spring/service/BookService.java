package spring.service;

import java.util.List;

import spring.model.Book;
import spring.model.StudentBook;

public interface BookService{
	public void addBook(Book b);
	public void updateBook(Book b);
	public List<Book> listBooks();
	public Book getBookById(int id);
	public void removeBook(int id);
	public List<Book> getBookByDepartmentId(int deptId);
}
