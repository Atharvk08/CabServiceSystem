package com.cabservice.profile_service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.profile_service.Model.User;
import com.cabservice.profile_service.Service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping("/user/")
	public String createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	@PutMapping("/user/")
	public String updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	@DeleteMapping("/user/{id}")
	public String createUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
