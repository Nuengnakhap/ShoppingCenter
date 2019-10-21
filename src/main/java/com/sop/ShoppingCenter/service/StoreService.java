package com.sop.ShoppingCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.repository.StoreRepository;

@Service
public class StoreService implements Services {

	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public Object getById(int id) {
		if (storeRepository.findById(id).isPresent()) {
			return storeRepository.findById(id);
		}
		return null;
	}

	@Override
	public Object getAll() {
		return storeRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Store store = (Store) item;
		store.setId(((int) storeRepository.count() + 1));
		storeRepository.save(store);
	}

	@Override
	public void update(int id, Object item) {
		if (storeRepository.findById(id).isPresent()) {
			Store store = (Store) item;
			store.setId(id);
			storeRepository.save(store);
		}
	}

	@Override
	public void deleteById(int id) {
		if (storeRepository.findById(id).isPresent()) {
			storeRepository.deleteById(id);
		}
	}

	@Override
	public void deleteAll() {
		storeRepository.deleteAll();
	}

}
