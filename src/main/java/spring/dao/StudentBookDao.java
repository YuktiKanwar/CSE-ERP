package spring.dao;

import java.util.List;

import spring.model.StudentBook;

public interface StudentBookDao {

	public void addStudentBook(StudentBook b);
	public void updateStudentBook(StudentBook b);
	public List<StudentBook> listStudentBooks();
	public StudentBook getStudentBookById(int id);
	public void removeStudentBook(int id);
}
