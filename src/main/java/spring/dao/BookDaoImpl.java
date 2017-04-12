package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Book;
import spring.model.Student;

@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addBook(Book b) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(b);
	}

	@Override
	public void updateBook(Book b) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooks() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Book> BooksList = session.createQuery("from Book").list();
		return BooksList;
	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Book b = (Book) session.load(Book.class, new Integer(id));
		return b;
	}

	@Override
	public void removeBook(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Book b = (Book) session.load(Book.class, new Integer(id));
		if(null != b){
			session.delete(b);
		}
	}

	@Override
	public List<Book> getBookByDepartmentId(int deptId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		List<Book> BooksList = session.createQuery("from Book where department_id="+deptId).list();
		return BooksList;
	}

}
