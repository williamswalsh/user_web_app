/**
 * Title:			UserInfo.java
 * @author			William Walsh
 * @version			1.0
 * @since			28-6-2017
 * Purpose:			UserInfo is a class which stores contact details about a user of a Website.
 * */
package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="USER_INFO") 
public class UserInfo {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")		
	private int id;
	
	@Id
	@Column(name="EMAIL")		
	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;
		
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="MOBILE")
	private String mobile;
	
	public UserInfo(){}
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}
}