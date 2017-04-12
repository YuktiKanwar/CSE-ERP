package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.StudentDao;
import spring.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	@Transactional
	public void addStudent(Student s) {
		// TODO Auto-generated method stub
		this.studentDao.addStudent(s);
	}

	@Override
	@Transactional
	public void updateStudent(Student s) {
		// TODO Auto-generated method stub
		this.studentDao.updateStudent(s);

	}

	@Override
	@Transactional
	public List<Student> listStudents() {
		// TODO Auto-generated method stub
		return this.studentDao.listStudents();
		
	}

	@Override
	@Transactional
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return this.studentDao.getStudentById(id);
	}

	@Override
	@Transactional
	public Student getStudentByRollNo(String rollNo) {
		// TODO Auto-generated method stub
		return this.studentDao.getStudentByRollNo(rollNo);
	}
	
	@Override
	@Transactional
	public List<Student> getStudentByDepartmentId(int deptId){
		return this.studentDao.getStudentByDepartmentId(deptId);
	}

	@Override
	@Transactional
	public void removeStudent(int id) {
		// TODO Auto-generated method stub
		this.studentDao.removeStudent(id);
	}

}
