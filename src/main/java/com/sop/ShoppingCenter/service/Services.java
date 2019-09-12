package com.sop.ShoppingCenter.service;


public interface Services {
	Object getById(int id);
	Object getAll();
	void create(Object item);
	void update(int id, Object item);
	void deleteById(int id);
	void deleteAll();
}
