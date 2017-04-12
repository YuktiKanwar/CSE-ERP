package spring.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Attendance;
import spring.model.Lecture;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAttendance(Attendance A) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(A);
	}

	@Override
	public void updateAttendance(Attendance A) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(A);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Attendance> listAttendancesByDateAndTimeTable(String date, int timetable_id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Attendance> AttendanceList = session.createQuery("from Attendance where timetable_id =" +timetable_id+"  AND date ='"+date+"'").list();
		return AttendanceList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Attendance getAttendanceByDateAndTimeTableAndStudent(String date, int timetable_id, int student_id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Attendance> AttendanceList = session.createQuery("from Attendance where timetable_id =" +timetable_id+"  AND date = '"+date+"' AND student_id= "+student_id).list();
		if(AttendanceList.isEmpty())
			return null;
		else
			{
			Attendance attendance = AttendanceList.get(0);
			return attendance;
			}
		
	}
	
	@Override
	public void removeAttendance(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Attendance a = (Attendance) session.load(Attendance.class, new Integer(id));
		if(null != a){
			session.delete(a);
		}
	}
}