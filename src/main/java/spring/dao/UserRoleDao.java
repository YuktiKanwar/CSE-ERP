package spring.dao;

import java.util.List;

import spring.model.UserRole;

public interface UserRoleDao {
	public void addUserRole(UserRole ur);
	public void updateUserRole(UserRole ur);
	public List<UserRole> listUserRoles();
	public void removeUserRole(UserRole ur);
}
