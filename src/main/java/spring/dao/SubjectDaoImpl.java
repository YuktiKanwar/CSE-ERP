package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Student;
import spring.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Subject getSubjectById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Subject s = (Subject) session.load(Subject.class, new Integer(id));
		return s;
	}
	
	@Override
	public List<Subject> listSubjects(){
		Session session = this.sessionFactory.getCurrentSession();
		List<Subject> SubjectsList = session.createQuery("from Subject").list();
		return SubjectsList;
	}

}
