package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		System.out.println("AddUserRole");
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ur);
	}

	@Override
	public void updateUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		System.out.println("UpdateUserRole");
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ur);
	}

	@Override
	public List<UserRole> listUserRoles() {
		// TODO Auto-generated method stub
		System.out.println("ListUserRoles");
		return null;
	}
	
	@Override
	public void removeUserRole(UserRole ur) {
		// TODO Auto-generated method stub
		System.out.println("RemoveUserRoles");
		Session session = this.sessionFactory.getCurrentSession();
			session.delete(ur);
	}

}
