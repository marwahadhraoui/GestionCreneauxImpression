package com.plateformeDev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateformeDev.entities.User;
import com.plateformeDev.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Permet l'accès depuis d'autres domaines (utile pour le frontend)
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/secretaires")
	public List<User> getSecretaires(){
		return userService.getSecretaires();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PostMapping
	public User createUser(@RequestBody User u) {
		return userService.saveUser(u);	
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") int id,@RequestBody User u) {
		u.setId(id);
		return userService.updateUser(u);
	}
	
	@DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
	
	
}
