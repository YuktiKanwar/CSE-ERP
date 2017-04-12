package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Book;
import spring.model.StudentBook;
@Repository
public class StudentBookDaoImpl implements StudentBookDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addStudentBook(StudentBook sb) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(sb);
	}

	@Override
	public void updateStudentBook(StudentBook sb) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(sb);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<StudentBook> listStudentBooks() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<StudentBook> StudentBooksList = session.createQuery("from StudentBook").list();
		return StudentBooksList;
	}

	@Override
	public StudentBook getStudentBookById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		StudentBook sb = (StudentBook) session.load(StudentBook.class, new Integer(id));
		return sb;
	}

	@Override
	public void removeStudentBook(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		StudentBook sb = (StudentBook) session.load(StudentBook.class, new Integer(id));
		if(null != sb){
			session.delete(sb);
		}
	}

}
