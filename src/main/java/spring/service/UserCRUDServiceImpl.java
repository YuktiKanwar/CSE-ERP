package spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.User;
import spring.model.UserRole;
import spring.dao.UserDao;
import spring.dao.UserRoleDao;

@Service("userCRUDService")
public class UserCRUDServiceImpl implements UserCRUDService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	@Transactional
	public void addUser(User p) {
		this.userDao.addUser(p);
	}

	@Override
	@Transactional
	public void updateUser(User p) {
		this.userDao.updateUser(p);
	}
	
	@Override
	@Transactional
	public List<User> listUsersByRoleFacultyAndHOD(){
		return this.userDao.listUsersByRoleFacultyAndHOD();
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	@Transactional
	public User getUserByUserName(String username) {
		return this.userDao.findByUserName(username);
	}
	
	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(String username) {
		User user = this.userDao.findByUserName(username);
		Set<UserRole> userRoles = user.getUserRole();
		for (UserRole userRole : userRoles) {
		    this.userRoleDao.removeUserRole(userRole);
		}
		this.userDao.removeUser(user.getId());
	}

}
