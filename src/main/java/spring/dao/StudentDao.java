package spring.dao;

import java.util.List;

import spring.model.Student;

public interface StudentDao {

	public void addStudent(Student s);
	public void updateStudent(Student s);
	public List<Student> listStudents();
	public Student getStudentById(int id);
	public Student getStudentByRollNo(String rollNo);
	public void removeStudent(int id);
}
