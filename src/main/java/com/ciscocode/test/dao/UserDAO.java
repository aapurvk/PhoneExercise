package com.ciscocode.test.dao;

import java.util.List;
import java.util.UUID;

import com.ciscocode.test.entity.Phone;
import com.ciscocode.test.entity.User;

public interface UserDAO {

	public void addUser(User theUser);

	public User findByUserId(UUID userId);

	public void deleteByUserId(UUID userId);

	public List<User> getAllUsers();

	public void savePhone(Phone phoneList);

	public Phone findPhoneById(UUID phoneId);

	public List<Phone> getUserPhone(UUID userId);

	public void deleteUserPhone(UUID phoneId);

	public void deletePhone(List<Phone> thePhone);

}
