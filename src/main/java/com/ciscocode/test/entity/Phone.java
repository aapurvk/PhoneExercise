package com.ciscocode.test.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.ciscocode.test.validator.PhoneModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phone")
public class Phone {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "phone_id", updatable = false, nullable = false)
	private UUID phoneId;
	
	@Column(name = "phone_name")
	@NotEmpty(message = "Please provide Phone Name")
	@Size(max = 20, message = "Phone number should not be greater than 20")
	private String phoneName;
	
	@Column(name = "phone_number")
	@NotEmpty(message = "Please provide Phone Number")
	@Pattern(regexp="^\\+(353|44)(\\s*\\d){9,12}$", message="Mobile number is invalid")
	private String phoneNumber;
	
	@Column(name = "phone_model")
	@NotEmpty(message = "Please provide Phone Model")
	@PhoneModel
	private String phoneModel;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="user_detail_id")
	@JsonIgnore
	private User user;
	
	public Phone() {
		
	}

	public Phone(UUID phoneId, String phoneName, String phoneNumber, String phoneModel) {
		this.phoneName = phoneName;
		this.phoneNumber = phoneNumber;
		this.phoneModel = phoneModel;
	}

	public UUID getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(UUID phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Phone [phoneId=" + phoneId + ", phoneName=" + phoneName + ", phoneNumber=" + phoneNumber
				+ ", phoneModel=" + phoneModel + "]";
	}

}
