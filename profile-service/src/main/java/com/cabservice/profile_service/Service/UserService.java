package com.cabservice.profile_service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.profile_service.Model.User;
import com.cabservice.profile_service.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public Optional<User> getUserById(Long id) {
		return repository.findById(id);
	}
	
	public String createUser(User user) {
		repository.save(user);
		return "Success";
	}
	
	public String updateUser(User user) {
		repository.save(user);
		return "Success";
	}
	
	public String deleteUser(Long id) {
		repository.deleteById(id);
		return "Success";
	}
	
	
}
