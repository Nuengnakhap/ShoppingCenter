package com.sop.ShoppingCenter.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Category extends BaseEntity {

	@Id
	private int id;
	private String name;
	private String description;
	
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	Product product;
	
	public Category() {
		super();
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
