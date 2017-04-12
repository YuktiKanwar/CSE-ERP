package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.StudentBookDao;
import spring.model.StudentBook;
@Service("studentBookService")
public class StudentBookServiceImpl implements StudentBookService {
	@Autowired
	private StudentBookDao studentBookDao;
	@Override
	@Transactional
	public void addStudentBook(StudentBook b) {
		// TODO Auto-generated method stub
		this.studentBookDao.addStudentBook(b);
	}
	@Transactional
	@Override
	public void updateStudentBook(StudentBook b) {
		// TODO Auto-generated method stub
		this.studentBookDao.updateStudentBook(b);
	}
	@Transactional
	@Override
	public List<StudentBook> listStudentBooks() {
		// TODO Auto-generated method stub
		return this.studentBookDao.listStudentBooks();
	}
	@Transactional
	@Override
	public StudentBook getStudentBookById(int id) {
		// TODO Auto-generated method stub
		return this.studentBookDao.getStudentBookById(id);
	}
	@Transactional
	@Override
	public void removeStudentBook(int id) {
		// TODO Auto-generated method stub
		this.studentBookDao.removeStudentBook(id);
	}

}
