package com.devopsbuddy.backend.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devopsbuddy.enums.PlanEnum;

@Entity
public class Plan {
	
	
	private static final Logger log = LoggerFactory.getLogger(Plan.class);
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	private String name;
	
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}


	public Plan(PlanEnum plansEnum) {
		
		this.setId(plansEnum.getId());
		this.setName(plansEnum.getPlanName());
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}
	
	
	


	

}
