package com.plateformeDev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateformeDev.entities.User;
import com.plateformeDev.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User saveUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public User updateUser(User u) {
		return userRepo.save(u);

	}

	@Override
	public void deleteUser(User u) {
			userRepo.delete(u);
	}

	@Override
	public void deleteUserById(int id) {
		userRepo.deleteById(id);
		
	}

	@Override
	public User getUser(int id) {
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getSecretaires() {
		return userRepo.findByRole("SECRETAIRE");
	}

}
