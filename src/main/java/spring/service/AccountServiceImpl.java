package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.AccountDao;
import spring.model.Account;
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;
	@Override
	@Transactional
	public void addAccount(Account a) {
		// TODO Auto-generated method stub
		this.accountDao.addAccount(a);
	}
	@Transactional
	@Override
	public void updateAccount(Account a) {
		// TODO Auto-generated method stub
		this.accountDao.updateAccount(a);
	}
	@Transactional
	@Override
	public List<Account> listAccounts() {
		// TODO Auto-generated method stub
		return this.accountDao.listAccounts();
	}
	@Transactional
	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return this.accountDao.getAccountById(id);
	}
	@Transactional
	@Override
	public void removeAccount(int id) {
		// TODO Auto-generated method stub
		this.accountDao.removeAccount(id);
	}
	@Transactional
	@Override
	public Account getAccountByStudentId(int student_id){
		return this.accountDao.getAccountByStudentId(student_id);
	}

}
