package spring.dao;

import java.util.List;

import spring.model.TimeTable;

public interface TimeTableDao {
	public void addTimeTable(TimeTable tt);
	public void updateTimeTable(TimeTable tt);
	public List<TimeTable> listTimeTables();
	public TimeTable getTimeTableById(int id);
	public void removeTimeTable(int id);
}
