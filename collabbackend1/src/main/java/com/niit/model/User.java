package com.niit.model;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
@Table(name="People_Name")
public class User {
	
@Id
private String username;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

private String firstname,lastname,role,password;
@Column(unique=true,nullable=false)
private String email,phonenumber;
@Column (name="on_line")
private boolean online;
public boolean isOnline() {
	return online;
}
public void setOnline(boolean online) {
	this.online = online;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
}
