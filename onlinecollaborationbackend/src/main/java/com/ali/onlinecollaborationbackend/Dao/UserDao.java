package com.ali.onlinecollaborationbackend.Dao;

import java.util.List;

import com.ali.onlinecollaborationbackend.model.User;;

public interface UserDao {
	List<User> listUsers();

	User getUserById(int id);

	boolean add(User user);

	boolean update(User user);

	boolean delete(User user);

	User getUserByUsername(String name);
	
	boolean userAuthenticate(String username,String password);
}
