package ru.sld.transactions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sld.transactions.dao.AccountDAO;
import ru.sld.transactions.model.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountDAO accDAO;
	
	@Override
	@Transactional
	public void addAccount(Account acc) {
		accDAO.addAccount(acc);
	}

	@Override
	@Transactional
	public Account getAccountById(Integer id) {		
		return accDAO.getAccountById(id);
	}
	
	@Override
	@Transactional
	public Account getAccountByNum(String num) {		
		return accDAO.getAccountByNum(num);
	}

	@Override
	@Transactional
	public void updateAccount(Account acc) {
		accDAO.updateAccount(acc);
	}

	@Override
	@Transactional
	public List<Account> listAccount() {
		return accDAO.listAccount();
	}

	@Override
	@Transactional
	public void removeAccount(Integer id) {
		accDAO.removeAccount(id);
	}
	
	@Override
	@Transactional
	public synchronized void deposit(	Account 	acc, 
										Integer 	amount) {
		Integer currentAmount = acc.getAmount();
		currentAmount += amount;
		System.out.println("Зачисление на счет "+acc.getNumber()+" суммы "+amount+" текущая сумма "+currentAmount);
		acc.setAmount(currentAmount);
		accDAO.updateAccount(acc);
	}
	
	@Override
	@Transactional
	public synchronized void withdraw(	Account 	acc, 
										Integer 	amount) throws RuntimeException {
		Integer currentAmount = acc.getAmount();
		if (amount > currentAmount)
			throw new RuntimeException("Сумма превышает остаток денежных средств");	    
		else {
			currentAmount -= amount; 
			System.out.println("Списание со счета "+acc.getNumber()+" суммы "+amount+" текущая сумма "+currentAmount);
			acc.setAmount(currentAmount);
			accDAO.updateAccount(acc);
		}
	}
	
	@Override
	@Transactional
	public synchronized void transaction(	Account 	debitedAcc, 
											Account 	proceedsAcc, 
											Integer 	amount) {
		if(debitedAcc != null && proceedsAcc != null) {
			withdraw(debitedAcc,amount);
		    deposit(proceedsAcc,amount);
		}
	}

}
