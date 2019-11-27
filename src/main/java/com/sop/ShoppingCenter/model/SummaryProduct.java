package com.sop.ShoppingCenter.model;

import java.util.ArrayList;

public class SummaryProduct {

	private int id;
	private String name;
	private int stock_quantity;
	private float price;
	private float stars;
	private int peopleStars;
	private ArrayList<String> images;
	private Category category;
	private SummaryStore store;

	public SummaryProduct(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.stock_quantity = product.getStock_quantity();
		this.price = product.getPrice();
		this.images = product.getImages();
		this.category = product.getCategory();
		this.store = new SummaryStore(product.getStore());
		this.stars = product.getStars();
		this.peopleStars = product.getPeople_stars();
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

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SummaryStore getStore() {
		return store;
	}

	public void setStore(SummaryStore store) {
		this.store = store;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

	public int getPeopleStars() {
		return peopleStars;
	}

	public void setPeopleStars(int peopleStars) {
		this.peopleStars = peopleStars;
	}

}
