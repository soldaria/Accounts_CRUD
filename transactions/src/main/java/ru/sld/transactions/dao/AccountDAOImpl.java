package ru.sld.transactions.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.sld.transactions.model.Account;
import ru.sld.transactions.model.User;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addAccount(Account acc) {
		this.sessionFactory.getCurrentSession().persist(acc);
	}

	@Override
	public Account getAccountById(Integer id) {
		Account acc = (Account) this.sessionFactory.getCurrentSession().load(Account.class, id);		
		return acc;
	}
	
	@Override
	public Account getAccountByNum(String num) {
		Session currentSession = this.sessionFactory.getCurrentSession();
        String hql = "from Account where number = :num";
        Query query = currentSession.createQuery(hql)
        		.setParameter("num", num);        
        return (Account) query.uniqueResult();
	}

	@Override
	public void updateAccount(Account acc) {
		this.sessionFactory.getCurrentSession().update(acc);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> listAccount() {
		return this.sessionFactory.getCurrentSession().createQuery("from Account").list();
	}

	@Override
	public void removeAccount(Integer id) {
		Account acc = getAccountById(id);
		if (acc != null) {
			this.sessionFactory.getCurrentSession().delete(acc);
		}
	}

}
