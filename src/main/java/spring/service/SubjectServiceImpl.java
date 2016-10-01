package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.SubjectDao;
import spring.model.Subject;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectDao subjectDao;
	@Override
	@Transactional
	public Subject getSubjectById(int id) {
		// TODO Auto-generated method stub
		return this.subjectDao.getSubjectById(id);
	}
	
	@Override
	@Transactional
	public List<Subject> listSubjects()
	{
		return this.subjectDao.listSubjects();
	}

}
