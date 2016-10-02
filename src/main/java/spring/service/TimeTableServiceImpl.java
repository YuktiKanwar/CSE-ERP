package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.TimeTableDao;
import spring.model.TimeTable;

@Service("timeTableService")
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private TimeTableDao timeTableDao;
	
	@Override
	@Transactional
	public void addTimeTable(TimeTable tt) {
		// TODO Auto-generated method stub
		this.timeTableDao.addTimeTable(tt);

	}

	@Override
	@Transactional
	public void updateTimeTable(TimeTable tt) {
		// TODO Auto-generated method stub
		this.timeTableDao.updateTimeTable(tt);
	}

	@Override
	@Transactional
	public List<TimeTable> listTimeTables() {
		// TODO Auto-generated method stub
		return this.timeTableDao.listTimeTables();
	}

	@Override
	@Transactional
	public TimeTable getTimeTableById(int id) {
		// TODO Auto-generated method stub
		return this.timeTableDao.getTimeTableById(id);
	}

	@Override
	@Transactional
	public void removeTimeTable(int id) {
		// TODO Auto-generated method stub
		this.timeTableDao.removeTimeTable(id);
	}

}
