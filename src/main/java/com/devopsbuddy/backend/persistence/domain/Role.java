package com.devopsbuddy.backend.persistence.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="role", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<UserRole> userRoles = new HashSet<>();
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
	
	

}
