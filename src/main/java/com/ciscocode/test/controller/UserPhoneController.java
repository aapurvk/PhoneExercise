package com.ciscocode.test.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciscocode.test.entity.Phone;
import com.ciscocode.test.entity.User;
import com.ciscocode.test.exceptionhandler.NotFoundException;
import com.ciscocode.test.service.UserService;

@RestController
@RequestMapping("/api")
public class UserPhoneController {

	private static final Logger LOGGER=LoggerFactory.getLogger(UserPhoneController.class);
	
	private UserService theUserService;
	
	@Autowired
	public UserPhoneController(UserService theUserService) {
		this.theUserService = theUserService;
	}
	
	/**
	 * Post Mapping to add user
	 */
	@PostMapping("/users")
	public User addUser(@Valid @RequestBody User theUser) {
		
		LOGGER.info(getClass() + ">> Adding new user");
		theUserService.addUser(theUser);
		return theUser;
	}
	
	
	/**
	 * Delete Mapping to delete a specific user
	 */
	@DeleteMapping("/users/{userId}")
	public String deleteByUserId(@PathVariable UUID userId) {
		
		LOGGER.info(getClass() + ">> Deleting the user");
		User tempUser = theUserService.findByUserId(userId);
		
		if (tempUser == null) {
			throw new NotFoundException("User Id not found - " + userId);
		}
		theUserService.deleteByUserId(userId);
		return "Deleted User with UserId - " + userId;
	}
	
	
	/**
	 * Get mapping to list all the Users in the system
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		LOGGER.info(getClass() + ">> Get list of all the users");
		return theUserService.getAllUsers();
	}
	
	
	/**
	 * Post Mapping to add phone to the user
	 */
	@PostMapping("/users/{userId}/phone")
	public Phone addUserPhone(@PathVariable UUID userId, @Valid @RequestBody Phone thePhone) {
		
		LOGGER.info(getClass() + ">> Adding Phone to a particular user");
		User tempUser = theUserService.findByUserId(userId);
		
		if (tempUser == null) {
			LOGGER.info(getClass() + ">> The given user Id is not present in Database");
			throw new NotFoundException("User Id not found - " + userId);
		}
		theUserService.savePhone(userId, thePhone);
		return thePhone;
	}
	
	
	/**
	 * Delete Mapping to delete user phone number
	 */
	@DeleteMapping("/users/{userId}/phone/{phoneId}")
	public String deleteUserPhone(@PathVariable UUID userId, @PathVariable UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Deleting the phone number for specified user");
		User tempUser = theUserService.findByUserId(userId);
		
		if (tempUser == null) {
			LOGGER.info(getClass() + ">> The given user Id is not present in Database");
			throw new NotFoundException("User Id not found - " + userId);
		}
		
		Phone tempPhone = theUserService.findPhoneById(phoneId);
		
		if (tempPhone == null) {
			LOGGER.info(getClass() + ">> The given phone Id is not present in Database");
			throw new NotFoundException("Phone Id not found - " + tempPhone);
		}
		
		theUserService.deleteUserPhone(userId, phoneId);
		return "Deleted Phone Id - "+ phoneId + " with UserId - " + userId;
	}
	
	
	/**
	 * Get mapping to return phones for specified user
	 */
	@GetMapping("/users/{userId}/phone")
	public List<Phone> getUserPhone(@PathVariable UUID userId) {
		
		LOGGER.info(getClass() + ">> Getting the list of all the phones with the user");
		User tempUser = theUserService.findByUserId(userId);
		
		if (tempUser == null) {
			LOGGER.info(getClass() + ">> The given user Id is not present in Database");
			throw new NotFoundException("User Id not found - " + userId);
		}
		return theUserService.getUserPhone(userId);
	}
	
	
	/**
	 * Put mapping to update user preferred phone for specified user
	 */
	@PutMapping("/users")
	public User updateUser(@Valid @RequestBody User theUser) {
		
		LOGGER.info(getClass() + ">> Updating the user details");
		theUserService.addUser(theUser);
		return null;
	}
	
}
