package com.sop.ShoppingCenter.service;


public interface Services {
	Object getById(int id);
	Object getAll();
	void create(Object item);
	Boolean update(int id, Object item);
	Boolean deleteById(int id);
	void deleteAll();
}
