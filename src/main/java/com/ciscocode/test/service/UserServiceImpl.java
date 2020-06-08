package com.ciscocode.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciscocode.test.dao.UserDAO;
import com.ciscocode.test.dao.UserDAOImpl;
import com.ciscocode.test.entity.Phone;
import com.ciscocode.test.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDAO theUserDAO;
	private User tempUser;
	
	@Autowired
	public UserServiceImpl(UserDAO theUserDAO) {
		this.theUserDAO = theUserDAO;
	}

	
	/**
	 * This will call the DAO function to add new user
	 */
	@Override
	@Transactional
	public void addUser(User theUser) {
		
		LOGGER.info(getClass() + ">> Adding user details ");
		theUserDAO.addUser(theUser);
	}

	
	/**
	 * This will call the DAO function to get user with user Id
	 */
	@Override
	@Transactional
	public User findByUserId(UUID userId) {
		
		LOGGER.info(getClass() + ">> Getting User with id " +userId);
		return theUserDAO.findByUserId(userId);
	}

	
	/**
	 * This will call the DAO function to delete the user and user's phone details
	 */
	@Override
	@Transactional
	public void deleteByUserId(UUID userId) {
		
		LOGGER.info(getClass() + ">> Delete user details with id - " +userId);
		List<Phone> thePhone = theUserDAO.getUserPhone(userId);
		if (thePhone != null) {
			LOGGER.info(getClass() + ">> In case the Phone details are present detele phone details first");
			theUserDAO.deletePhone(thePhone);
		}
		theUserDAO.deleteByUserId(userId);
	}

	
	/**
	 * This will call the DAO function to get list of all users
	 */
	@Override
	@Transactional
	public List<User> getAllUsers() {
		
		LOGGER.info(getClass() + ">> Getting list of all the users ");
		return theUserDAO.getAllUsers();
	}

	
	/**
	 * This will call the DAO function to save the phone details for that user
	 */
	@Override
	@Transactional
	public void savePhone(UUID userId ,Phone thePhone) {
		
		LOGGER.info(getClass() + ">> Save phone details for user id - " +userId);
		List<Phone> phoneList = new ArrayList<Phone>();
		tempUser = findByUserId(userId);
		phoneList.add(thePhone);
		thePhone.setUser(tempUser);
		theUserDAO.savePhone(thePhone);
	}

	
	/**
	 * This will call the DAO function to delete phone details for user
	 */
	@Override
	@Transactional
	public void deleteUserPhone(UUID userId, UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Delete phone details with id - " +phoneId);
		theUserDAO.deleteUserPhone(phoneId);
	}

	
	/**
	 * This will call the DAO function to get list of all the phones for that user
	 */
	@Override
	@Transactional
	public List<Phone> getUserPhone(UUID userId) {
		
		LOGGER.info(getClass() + ">> Get phone details for user id - " +userId);
		return theUserDAO.getUserPhone(userId);
	}

	
	/**
	 * This will call the DAO function to get the phone details by phone Id
	 */
	@Override
	public Phone findPhoneById(UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Get phone details for phone id - " +phoneId);
		return theUserDAO.findPhoneById(phoneId);
	}

}
