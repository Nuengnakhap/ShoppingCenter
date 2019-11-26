package com.sop.ShoppingCenter.model;

public class SummaryOrderDetail {
	private int id;
	private int order_id;
	private int quantity;
	private float price;
	private SummaryProduct product;

	public SummaryOrderDetail(OrderDetail detail) {
		super();
		this.id = detail.getId();
		this.order_id = detail.getOrder().getId();
		this.quantity = detail.getQuantity();
		this.price = detail.getPrice();
		this.product = new SummaryProduct(detail.getProduct());
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public SummaryProduct getProduct() {
		return product;
	}

	public void setProduct(SummaryProduct product) {
		this.product = product;
	}

}
