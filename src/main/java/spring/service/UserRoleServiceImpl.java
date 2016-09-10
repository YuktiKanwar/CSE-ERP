package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.UserRoleDao;
import spring.model.UserRole;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	@Transactional
	public void addUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		this.userRoleDao.addUserRole(ur);
	}

	@Override
	@Transactional
	public void updateUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		this.userRoleDao.updateUserRole(ur);

	}

	@Override
	@Transactional
	public List<UserRole> listUserRoles() {
		// TODO Auto-generated method stub
		return this.userRoleDao.listUserRoles();
	}

}
