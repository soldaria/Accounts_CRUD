package ru.sld.transactions.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sld.transactions.dao.UserDAO;
import ru.sld.transactions.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDAO userDAO;
		
	@Override
	@Transactional
	public void addUser(User user) {
		user.setPassword(encodeMD5(user.getPassword()));
		userDAO.addUser(user);
	}
	
	@Override
	@Transactional
	public User getUserById(Integer id) {
		return userDAO.getUserById(id);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	@Override
	@Transactional
	public List<User> listUser() {		
		return userDAO.listUser();	}
	
	@Override
	@Transactional
	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}
	
	@Override
	@Transactional
	public User validateUser(User user) {
		user.setPassword(encodeMD5(user.getPassword()));
		return userDAO.validateUser(user);
	}
	
	@Override
	@Transactional
	public void refreshUser(User user) {
		 userDAO.refreshUser(user);
	}
		
	public static String encodeMD5(String pass) {
	    String enc = DigestUtils.md5Hex(pass);
	    return enc;
	}

}
