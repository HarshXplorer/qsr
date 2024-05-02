package com.qsr.controller;

//import com.ecommerce.Iservice.IUserService;
import com.qsr.entity.User;
import com.qsr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	UserService service;

	// Get all Users
	@GetMapping("/user/all")
	public ResponseEntity<List<User>> retrieveAllUsers() {
		List<User> loginList = service.findAllValues();
		return new ResponseEntity<>(loginList, HttpStatus.OK);
	}

	// Get User by id
	@GetMapping("/user/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable Long id) {
		User login = service.findOne(id);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	//  Sign Up
	@PostMapping("/Adduser")
	public ResponseEntity<User> signUp(@RequestBody User ob) {
		User login = service.save(ob);
		return new ResponseEntity<>(login, HttpStatus.CREATED);
	}

	// Delete a user id
	@DeleteMapping("/delete/user/{id}")
	public String deleteUser(@PathVariable Long id) {
		service.delete(id);
		return "Record removed Successfully";
	}

	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User ob) {
		User login = service.update(ob);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	// Log in
	@GetMapping("/user/{email}/{pass}")
	public User logIn(@PathVariable String email, @PathVariable String pass) {
		User login = service.logIn(email, pass);
		return login;
	}

}
