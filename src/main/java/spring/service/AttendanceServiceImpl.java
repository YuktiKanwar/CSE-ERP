package spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.AttendanceDao;
import spring.model.Attendance;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;
	
	@Override
	@Transactional
	public void addAttendance(Attendance A) {
		// TODO Auto-generated method stub
		this.attendanceDao.addAttendance(A);
	}

	@Override
	@Transactional
	public void updateAttendance(Attendance A) {
		// TODO Auto-generated method stub
		this.attendanceDao.updateAttendance(A);
	}

	@Override
	@Transactional
	public List<Attendance> listAttendancesByDateAndTimeTable(String date, int timetable_id) {
		// TODO Auto-generated method stub
		return this.attendanceDao.listAttendancesByDateAndTimeTable(date,timetable_id);
	}
	
	@Override
	@Transactional
	public Attendance getAttendanceByDateAndTimeTableAndStudent(String date, int timetable_id, int student_id) {
		// TODO Auto-generated method stub
		return this.attendanceDao.getAttendanceByDateAndTimeTableAndStudent(date,timetable_id,student_id);
	}
	
	@Override
	@Transactional
	public void removeAttendance(int id) {
		// TODO Auto-generated method stub
		this.attendanceDao.removeAttendance(id);
	}


}
