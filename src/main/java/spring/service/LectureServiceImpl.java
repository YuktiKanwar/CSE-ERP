package spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.LectureDao;
import spring.dao.TimeTableDao;
import spring.dao.UserRoleDao;
import spring.model.Lecture;
import spring.model.TimeTable;
import spring.model.UserRole;

@Service("lectureService")
public class LectureServiceImpl implements LectureService {
	@Autowired
	private LectureDao lectureDao;
	@Autowired
	private TimeTableDao timeTableDao;
	
	@Override
	@Transactional
	public void addLecture(Lecture l) {
		// TODO Auto-generated method stub
		this.lectureDao.addLecture(l);
	}

	@Override
	@Transactional
	public void updateLecture(Lecture l) {
		// TODO Auto-generated method stub
		this.lectureDao.updateLecture(l);
	}

	@Override
	@Transactional
	public List<Lecture> listLectures() {
		// TODO Auto-generated method stub
		return this.lectureDao.listLectures();
	}
	

	@Override
	@Transactional
	public Lecture getLectureById(int id) {
		// TODO Auto-generated method stub
		return this.lectureDao.getLectureById(id);
	}
	
	@Override
	@Transactional
	public List<Lecture> getLectureByFacultyId(int facultyId) {
		// TODO Auto-generated method stub
		return this.lectureDao.getLectureByFacultyId(facultyId);
	}

	@Override
	@Transactional
	public void removeLecture(int id) {
		// TODO Auto-generated method stub
		this.lectureDao.removeLecture(id);
	}

}
