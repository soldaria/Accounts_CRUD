package ru.sld.transactions.dao;

import java.util.List;
import ru.sld.transactions.model.Account;

public interface AccountDAO {
	
	public void addAccount(Account acc);
	
	public Account getAccountById(Integer id);
	
	Account getAccountByNum(String num);
	
	public void updateAccount(Account acc);

	public List<Account> listAccount();

	public void removeAccount(Integer id);
		

}
