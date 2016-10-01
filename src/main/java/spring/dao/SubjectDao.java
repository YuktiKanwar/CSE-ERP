package spring.dao;

import java.util.List;

import spring.model.Subject;

public interface SubjectDao {
	public Subject getSubjectById(int id);
	public List<Subject> listSubjects();
}
