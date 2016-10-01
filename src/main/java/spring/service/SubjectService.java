package spring.service;

import java.util.List;

import spring.model.Subject;

public interface SubjectService {
	public Subject getSubjectById(int id);
	public List<Subject> listSubjects();
}
