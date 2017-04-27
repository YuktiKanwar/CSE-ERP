package spring.dao;

import java.util.List;

import spring.model.Account;

public interface AccountDao {

	public void addAccount(Account a);
	public void updateAccount(Account a);
	public List<Account> listAccounts();
	public Account getAccountById(int id);
	public void removeAccount(int id);
	public Account getAccountByStudentId(int student_id);
}
