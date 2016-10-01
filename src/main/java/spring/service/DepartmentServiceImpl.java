package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.DepartmentDao;
import spring.model.Department;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	@Override
	@Transactional
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		return this.departmentDao.getDepartmentById(id);
	}

}
