package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Billing extends BaseEntity {
	
	@Id
	private int id;
	
//	@OneToOne
//	@JoinColumn(nullable = false)
//    Order order;

	public Billing() {
		super();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
