package com.shopme.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String email;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(name="first_name", length = 64, nullable = false)
	private String firstName;
	
	@Column(name="last_name", length = 64, nullable = false)
	private String lastName;
	
	@Column(length = 64)
	private String photos;
	
	private boolean enabled;
	
	
	@ManyToMany
	@JoinTable(
			name="user_roles" ,
			joinColumns= @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private Set<Role> roles = new HashSet<>();


	public User() {
		
	}


	public User( String email, String password, String firstName, String lastName) {
		
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	// method to add role using role set 
	public void addRole(Role role) {
		this.roles.add(role);
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", roles=" + roles + "]";
	}
	
	
	
	

}
