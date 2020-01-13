package com.novabank.to;

import java.util.Date;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int archetype;
	private String ssn;
	private String homePhone;
	private String mobilePhone;
	private String email;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String userName;
	private String password;
	private Date dateCreated;
	
	// Constructors
	public User() {
		
	};
	
	public User (int userId,
			String firstName,
			String lastName,
			int archetype,
			String ssn,
			String homePhone,
			String mobilePhone,
			String email,
			String streetAddress,
			String city,
			String state,
			String country,
			String zip,
			String userName,
			String password,
			Date dateCreated) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.archetype = archetype;
		this.ssn = ssn;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.userName = userName;
		this.password = password;
		this.dateCreated = dateCreated;
	}
	
	// Overrides
	@Override
	public String toString() {
		
		return "Username: " + getUserName() + "\nType: " + getArchetype();
	}
	
	// Mutators and Accessors
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getArchetype() {
		return archetype;
	}
	public void setArchetype(int archetype) {
		this.archetype = archetype;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	// public methods
}
