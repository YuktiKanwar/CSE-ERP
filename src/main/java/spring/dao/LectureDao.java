package spring.dao;

import java.util.List;

import spring.model.Lecture;
import spring.model.Student;

public interface LectureDao {
	public void addLecture(Lecture l);
	public void updateLecture(Lecture l);
	public List<Lecture> listLectures();
	public Lecture getLectureById(int id);
	public void removeLecture(int id);
	public List<Lecture> getLectureByFacultyId(int facultyId);
}
