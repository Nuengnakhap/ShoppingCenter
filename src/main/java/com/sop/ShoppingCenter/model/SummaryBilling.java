package com.sop.ShoppingCenter.model;

public class SummaryBilling {

	private int id;
	private int order_id;
	private Customer customer;

	public SummaryBilling(Billing billing) {
		super();
		this.id = billing.getId();
		this.order_id = billing.getOrder().getId();
		this.customer = billing.getCustomer();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
