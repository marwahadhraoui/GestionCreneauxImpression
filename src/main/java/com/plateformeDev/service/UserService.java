package com.plateformeDev.service;

import java.util.List;

import com.plateformeDev.entities.User;

public interface UserService {
	User saveUser(User u);
	User updateUser(User u);
	void deleteUser(User u );
	void deleteUserById(int id);
	User getUser(int id);
	List<User> getAllUsers();
	List<User> getSecretaires();
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);

}
