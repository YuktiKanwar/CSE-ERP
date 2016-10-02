package spring.dao;

import java.util.List;

import spring.model.User;

public interface UserDao {

	User findByUserName(String username);
	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public List<User> listUsersByRoleFacultyAndHOD();
}