package it.dstech.rubricaClient.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Contact> listContact;
	
	public User() {
		listContact = new ArrayList<Contact>(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Contact> getListContact() {
		return listContact;
	}

	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password +  "]";
	}
	

}
