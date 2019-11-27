package com.sop.ShoppingCenter.model;

public class SummaryRating {

	private int id;
	private int orderDetail_id;
	private int billing_id;
	private int customer_id;
	private float stars;

	public SummaryRating(Rating rating) {
		super();
		this.id = rating.getId();
		this.orderDetail_id = rating.getOrderDetail().getId();
		this.billing_id = rating.getBilling().getId();
		this.customer_id = rating.getCustomer().getId();
		this.stars = rating.getStars();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderDetail_id() {
		return orderDetail_id;
	}

	public void setOrderDetail_id(int orderDetail_id) {
		this.orderDetail_id = orderDetail_id;
	}

	public int getBilling_id() {
		return billing_id;
	}

	public void setBilling_id(int billing_id) {
		this.billing_id = billing_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

}
