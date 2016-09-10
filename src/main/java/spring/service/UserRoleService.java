package spring.service;

import java.util.List;

import spring.model.UserRole;

public interface UserRoleService {
	public void addUserRole(UserRole ur);
	public void updateUserRole(UserRole ur);
	public List<UserRole> listUserRoles();
}
