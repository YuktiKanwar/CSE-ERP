package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Student;
import spring.model.User;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private SessionFactory sessionFactory;
		
		//Method for CRUD
		@Override
		public void addStudent(Student s) {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(s);
		}

		@Override
		public void updateStudent(Student s) {
			Session session = this.sessionFactory.getCurrentSession();
			session.update(s);
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Student> listStudents() {
			Session session = this.sessionFactory.getCurrentSession();
			List<Student> StudentsList = session.createQuery("from Student").list();
			return StudentsList;
		}

		@Override
		public Student getStudentById(int id) {
			Session session = this.sessionFactory.getCurrentSession();		
			Student s = (Student) session.load(Student.class, new Integer(id));
			return s;
		}
		
		@Override
		public Student getStudentByRollNo(String rollNo) {
			Session session = this.sessionFactory.getCurrentSession();		
			Student s = (Student) session.load(Student.class, new String(rollNo));
			return s;
		}

		@Override
		public void removeStudent(int id) {
			Session session = this.sessionFactory.getCurrentSession();
			Student s = (Student) session.load(Student.class, new Integer(id));
			if(null != s){
				session.delete(s);
			}
		}

}
