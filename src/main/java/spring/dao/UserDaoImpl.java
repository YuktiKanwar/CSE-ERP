package spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where username=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	//Method for CRUD
	@Override
	public void addUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}

	@Override
	public void updateUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.openSession();
		List<User> UsersList = session.createQuery("from User").list();
		return UsersList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User p = (User) session.load(User.class, new Integer(id));
		return p;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		if(null != u){
			session.delete(u);
		}
	}
}