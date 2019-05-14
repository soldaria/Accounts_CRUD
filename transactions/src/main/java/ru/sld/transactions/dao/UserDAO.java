package ru.sld.transactions.dao;

import java.util.List;

import ru.sld.transactions.model.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public User getUserById(Integer id);
	
	public void updateUser(User user);

	public List<User> listUser();

	public void removeUser(Integer id);
	
	public User validateUser(User user);

	void refreshUser(User user);
}
