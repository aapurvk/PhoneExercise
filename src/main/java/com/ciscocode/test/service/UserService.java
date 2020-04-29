package com.ciscocode.test.service;

import java.util.List;
import java.util.UUID;

import com.ciscocode.test.entity.Phone;
import com.ciscocode.test.entity.User;

public interface UserService {

	public void addUser(User theUser);

	public User findByUserId(UUID userID);

	public void deleteByUserId(UUID userID);

	public List<User> getAllUsers();

	public void savePhone(UUID userId, Phone thePhone);

	public void deleteUserPhone(UUID userId, UUID phoneId);

	public List<Phone> getUserPhone(UUID userId);

	public Phone findPhoneById(UUID phoneId);

}
