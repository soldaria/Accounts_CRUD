package ru.sld.transactions.service;

import java.util.List;

import ru.sld.transactions.model.Account;

public interface AccountService {
	
	public void addAccount(Account acc);
	
	public Account getAccountById(Integer id);
	
	Account getAccountByNum(String num);
	
	public void updateAccount(Account acc);

	public List<Account> listAccount();

	public void removeAccount(Integer id);

	void deposit(Account acc, Integer amount);

	void withdraw(Account acc, Integer amount) throws RuntimeException;

	void transaction(Account debitedAcc, Account proceedsAcc, Integer amount);	
}