package spring.dao;

import java.util.List;

import spring.model.Lecture;

public interface LectureDao {
	public void addLecture(Lecture l);
	public void updateLecture(Lecture l);
	public List<Lecture> listLectures();
	public Lecture getLectureById(int id);
	public void removeLecture(int id);
}
