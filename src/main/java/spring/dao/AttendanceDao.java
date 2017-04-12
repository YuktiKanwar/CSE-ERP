package spring.dao;

import java.util.Date;
import java.util.List;

import spring.model.Attendance;

public interface AttendanceDao {
	
	public void addAttendance(Attendance A);
	public void updateAttendance(Attendance A);
	public List<Attendance> listAttendancesByDateAndTimeTable(String date, int timetable_id);
	public Attendance getAttendanceByDateAndTimeTableAndStudent(String date, int timetable_id, int student_id);
	public void removeAttendance(int id);
}