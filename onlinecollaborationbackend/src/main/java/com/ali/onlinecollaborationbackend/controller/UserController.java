package com.ali.onlinecollaborationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ali.onlinecollaborationbackend.Dao.UserDao;
import com.ali.onlinecollaborationbackend.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	// -------CREATE A USER
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setStatus('y');
		user.setIsOnline('n');
		userDao.add(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	// --------LIST USERS
	@GetMapping("/listUsers")
	public ResponseEntity<List<User>> listUsers() {
		List<User> users = userDao.listUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// --------Retreive A SINGLE USER BY ID
	@GetMapping("/user/{id}")
	public ResponseEntity<User> singleUser(@PathVariable("id") int id) {
		User user = userDao.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// --------Update A SINGLE USER BY ID
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		User currentUser = userDao.getUserById(id);
		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			currentUser.setFullName(user.getFullName());
			currentUser.setUserName(user.getUserName());
			currentUser.setEmail(user.getEmail());
			userDao.update(currentUser);
			return new ResponseEntity<User>(currentUser, HttpStatus.OK);
		}
	}

	// ---------Delete A Single by user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		User currentUser = userDao.getUserById(id);
		if (currentUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {

			userDao.delete(currentUser);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

	}

	// ----------Authenticate a User
	@PostMapping("/user/validate")
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		if (userDao.userAuthenticate(user.getUserName(),user.getPassword())) {
			user=userDao.getUserByUsername(user.getUserName());
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
	}
}