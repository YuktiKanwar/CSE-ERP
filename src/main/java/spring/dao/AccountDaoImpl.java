package spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Account;
import spring.model.Attendance;
@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addAccount(Account a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(a);
	}

	@Override
	public void updateAccount(Account a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> listAccounts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Account> AccountsList = session.createQuery("from Account").list();
		return AccountsList;
	}

	@Override
	public Account getAccountById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Account a = (Account) session.load(Account.class, new Integer(id));
		return a;
	}
	
	@Override
	public Account getAccountByStudentId(int student_id) {
		Session session = this.sessionFactory.getCurrentSession();		
		List<Account> AccountList = session.createQuery("from Account where student_id =" +student_id).list();
		if(AccountList.isEmpty())
			return null;
		else
			{
			Account account = AccountList.get(0);
			return account;
			}
	}

	@Override
	public void removeAccount(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Account a = (Account) session.load(Account.class, new Integer(id));
		if(null != a){
			session.delete(a);
		}
	}

}
