package com.ciscocode.test.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "user_id", updatable = false, nullable = false)
	private UUID userId;
	
	@Column(name = "user_name")
	@NotEmpty(message = "Please provide a UserName")
	@Size(max = 25, message = "User Name should not be greater than 15")
	private String userName;
	
	@Column(name = "password")
	@NotEmpty(message = "Please provide the password")
	@Size(max = 10, min = 8, message = "Password should be greater than 8 and less than 10")
	private String password;
	
	@Column(name = "email_address")
	@NotEmpty(message = "Please provide Email Address")
	@Email
	private String emailAddress;
	
	@Column(name = "preferred_number")
	@NotEmpty(message = "Please provide Preferred Phone Number")
	@Pattern(regexp="^\\+(353|44)(\\s*\\d){9,12}$", message="Mobile number is invalid")
	private String preferredPhoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private List<Phone> phone;
	
	public User() {
		
	}

	public User(UUID userId, String userName, String password, String emailAddress, String preferredPhoneNumber) {
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.preferredPhoneNumber = preferredPhoneNumber;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPreferredPhoneNumber() {
		return preferredPhoneNumber;
	}

	public void setPreferredPhoneNumber(String preferredPhoneNumber) {
		this.preferredPhoneNumber = preferredPhoneNumber;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", emailAddress="
				+ emailAddress + ", preferredPhoneNumber=" + preferredPhoneNumber + "]";
	}
	
}
