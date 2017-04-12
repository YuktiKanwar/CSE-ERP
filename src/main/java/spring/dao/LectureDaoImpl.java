package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Lecture;

@Repository
public class LectureDaoImpl implements LectureDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addLecture(Lecture l) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(l);
	}

	@Override
	public void updateLecture(Lecture l) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lecture> listLectures() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Lecture> LecturesList = session.createQuery("from Lecture").list();
		return LecturesList;
	}

	@Override
	public Lecture getLectureById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Lecture l = (Lecture) session.load(Lecture.class, new Integer(id));
		return l;
	}
	
	@Override
	public List<Lecture> getLectureByFacultyId(int facultyId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();	
		List<Lecture> LecturesList = session.createQuery("from Lecture where user_id="+facultyId).list();
		return LecturesList;
	}

	@Override
	public void removeLecture(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Lecture l = (Lecture) session.load(Lecture.class, new Integer(id));
		if(null != l){
			session.delete(l);
		}
	}

}
