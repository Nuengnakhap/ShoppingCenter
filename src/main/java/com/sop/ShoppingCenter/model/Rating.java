package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Rating extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float stars;

	@OneToOne
	@JoinColumn(nullable = false)
	private Billing billing;

	@OneToOne
	@JoinColumn(nullable = false)
	private OrderDetail orderDetail;

	@ManyToOne
	@JoinColumn(nullable = false)
	Customer customer;

	@Transient
	private int billing_id;

	@Transient
	private int orderDetail_id;

	public Rating() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public int getBilling_id() {
		return billing_id;
	}

	public void setBilling_id(int billing_id) {
		this.billing_id = billing_id;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public int getOrderDetail_id() {
		return orderDetail_id;
	}

	public void setOrderDetail_id(int orderDetail_id) {
		this.orderDetail_id = orderDetail_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
