package com.shopme.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40, nullable = false, unique = true)
	private String name;
	
	@Column(length = 150, nullable = false)
	private String description;

	public Role() {
		
	}
	
	public Role(Integer id) {
		this.id = id;
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public Role(String name, String description) {
		
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}
	

	
	



}
