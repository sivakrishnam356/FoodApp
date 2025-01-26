package com.tap.dao;
import com.tap.model.User;
import java.util.List;

import com.tap.model.User;

public interface UserDAO {

	int addUser(User user);
	User getUser(String userEmail);
	void updateUser(User user,int userId);
	void deleteUser(int userId);
	List<User> getAllUsers();
	
}
