package com.sop.ShoppingCenter.controller;


public interface Controllers {
	Object getById(int id);
	Object getAll();
	Object create(Object item);
	Object update(int id, Object item);
	Object deleteById(int id);
	void deleteAll();
}
