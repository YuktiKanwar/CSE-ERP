package spring.service;

import java.util.List;

import spring.model.Book;
import spring.model.StudentBook;

public interface StudentBookService{

	public void addStudentBook(StudentBook b);
	public void updateStudentBook(StudentBook b);
	public List<StudentBook> listStudentBooks();
	public StudentBook getStudentBookById(int id);
	public void removeStudentBook(int id);
}
