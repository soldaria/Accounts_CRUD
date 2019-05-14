package ru.sld.transactions.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sld.transactions.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		this.sessionFactory.getCurrentSession().persist(user);		
	}
	
	@Override
	public void refreshUser(User user) {
		this.sessionFactory.getCurrentSession().refresh(user);
	}
	
	@Override
	public User getUserById(Integer id) {
		User user = (User) this.sessionFactory.getCurrentSession().load(User.class, id);		
		return user;
	}
	
	@Override
	public void updateUser(User user) {		
		this.sessionFactory.getCurrentSession().update(user);		
	}	

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUser() {			
		return this.sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public void removeUser(Integer id) {		
		User user = getUserById(id);
		if (user != null) {
			this.sessionFactory.getCurrentSession().delete(user);
		}

	}
	
	@Override
	public User validateUser(User user) {		
		Session currentSession = this.sessionFactory.getCurrentSession();
        String hql = "from User where login = :login and password = :password";
        Query query = currentSession.createQuery(hql)
        		.setParameter("login", user.getLogin())
        		.setParameter("password", user.getPassword());
        
        return (User) query.uniqueResult();
	}

}
