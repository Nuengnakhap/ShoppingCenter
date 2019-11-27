package com.sop.ShoppingCenter.model;

public class SummaryStore {
	private int id;
	private String storeName;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private float stars;
	private int peopleStars;

	public SummaryStore(Store store) {
		super();
		this.id = store.getId();
		this.storeName = store.getStoreName();
		this.phone = store.getPhone();
		this.email = store.getEmail();
		this.address = store.getAddress();
		this.street = store.getStreet();
		this.city = store.getCity();
		this.state = store.getState();
		this.zipCode = store.getZipCode();
		this.firstName = store.getCustomer().getFirstName();
		this.lastName = store.getCustomer().getLastName();
		this.stars = store.getStars();
		this.peopleStars = store.getPeople_stars();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
