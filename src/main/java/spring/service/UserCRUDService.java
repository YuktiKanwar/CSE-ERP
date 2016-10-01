package spring.service;

import java.util.List;

import spring.model.User;

public interface UserCRUDService {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserByUserName(String username);
	public User getUserById(int id);
	public void removeUser(String username);
	
}