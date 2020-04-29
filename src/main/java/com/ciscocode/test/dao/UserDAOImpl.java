package com.ciscocode.test.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ciscocode.test.controller.UserPhoneController;
import com.ciscocode.test.entity.Phone;
import com.ciscocode.test.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserDAOImpl.class);
	
	private EntityManager theEntityManager;
	
	public UserDAOImpl(EntityManager theEntityManager) {
		this.theEntityManager = theEntityManager;
	}
	

	/**
	 * Function to add the user in the Database
	 */
	@Override
	public void addUser(User theUser) {
		
		LOGGER.info(getClass() + ">> Save/Update user details in database");
		Session currentSession = theEntityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theUser);
	}

	
	/**
	 * Function to find a user on the basis of user Id
	 */
	@Override
	public User findByUserId(UUID userId) {
		
		LOGGER.info(getClass() + ">> Find the user with user id - " +userId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, userId);
		return theUser;
	}

	
	/**
	 * Function to delete a user with user Id
	 */
	@Override
	public void deleteByUserId(UUID userId) {
		
		LOGGER.info(getClass() + ">> Delete the user with user id - " +userId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from User where userId=:userId");
		theQuery.setParameter("userId", userId);
		theQuery.executeUpdate();
	}

	
	/**
	 * Function to get list of all users in Database
	 */
	@Override
	public List<User> getAllUsers() {
		
		LOGGER.info(getClass() + ">> Getting list of all users");
		Session currentSession = theEntityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	
	/**
	 * Function to add user's phone in the Database
	 */
	@Override
	public void savePhone(UUID userId, Phone thePhone) {
		
		LOGGER.info(getClass() + ">> Save phone number for user with id - " +userId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, userId);
		theUser.add(thePhone);
		currentSession.saveOrUpdate(theUser);
	}

	
	/**
	 * Function to find the phone details from phone Id
	 */
	@Override
	public Phone findPhoneById(UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Find the phone with id - " +phoneId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		Phone thePhone = currentSession.get(Phone.class, phoneId);
		return thePhone;
	}
	

	/**
	 * Function to list all the phone details for specific user
	 */
	@Override
	public List<Phone> getUserPhone(UUID userId) {
		
		LOGGER.info(getClass() + ">> Get phone details for user id - " +userId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		User theUser = currentSession.get(User.class, userId);
		List<Phone> thePhone = theUser.getPhone();
		return thePhone;
	}

	
	/**
	 * Function to delete the phone details for that user in Database
	 */
	@Override
	public void deleteUserPhone(UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Delete phone details with id - " +phoneId);
		Session currentSession = theEntityManager.unwrap(Session.class);
		Query theQuery = currentSession.createSQLQuery("delete from phone where phone_id=:phoneId");
		theQuery.setParameter("phoneId", phoneId);
		theQuery.executeUpdate();
	}

	
	/**
	 * Function to delete the phone phone object in case the user is deleted
	 */
	@Override
	public void deletePhone(List<Phone> thePhone) {
		
		LOGGER.info(getClass() + ">> Delete the phone object for the given user ");
		Session currentSession = theEntityManager.unwrap(Session.class);
		for(Phone tempPhone : thePhone) {
			currentSession.delete(tempPhone);
		}
	}


	/**
	 * Function to delete the user phone mapping after for deleting the phone
	 */
	@Override
	public void deleteUserPhoneMapping(UUID userId, UUID phoneId) {
		
		LOGGER.info(getClass() + ">> Delete the user phone mapping for giver userId and phoneId ");
		Session currentSession = theEntityManager.unwrap(Session.class);
		Query theQuery = currentSession.createSQLQuery("delete from user_phone where user_id =:userId and phone_id =:phoneId");
		theQuery.setParameter("phoneId", phoneId);
		theQuery.setParameter("userId", userId);
		theQuery.executeUpdate();
	}

}
