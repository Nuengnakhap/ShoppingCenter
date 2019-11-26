package com.sop.ShoppingCenter.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Product extends BaseEntity {

	@Id
	private int id;
	private String name;
	private int stock_quantity;
	private float price;

	@OneToMany(mappedBy = "product")
	private List<Image> image;
	
	@Transient
	private List<String> images;

	@ManyToOne
	@JoinColumn(nullable = false)
	Category category;

	@ManyToOne
	@JoinColumn(nullable = false)
	Store store;

	@Transient
	private int category_id;

	public Product() {
		super();
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public List<Image> getImage() {
		return image;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}