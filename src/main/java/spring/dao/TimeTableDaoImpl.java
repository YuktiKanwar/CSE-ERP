package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.TimeTable;

@Repository
public class TimeTableDaoImpl implements TimeTableDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addTimeTable(TimeTable tt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tt);
	}

	@Override
	public void updateTimeTable(TimeTable tt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(tt);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TimeTable> listTimeTables() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<TimeTable> TimeTablesList = session.createQuery("from TimeTable").list();
		return TimeTablesList;
	}

	@Override
	public TimeTable getTimeTableById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		TimeTable tt = (TimeTable) session.load(TimeTable.class, new Integer(id));
		return tt;
	}

	@Override
	public void removeTimeTable(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		TimeTable tt = (TimeTable) session.load(TimeTable.class, new Integer(id));
		if(null != tt){
			session.delete(tt);
		}
	}

}
