package com.sop.ShoppingCenter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.model.SummaryStore;
import com.sop.ShoppingCenter.repository.StoreRepository;

@Service
public class StoreService implements Services {

	@Autowired
	StoreRepository storeRepository;

	@Override
	public Object getById(int id) {
		if (storeRepository.findById(id).isPresent()) {
			return storeRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Object getAll() {
		List<SummaryStore> stores = new ArrayList<SummaryStore>(); 
		for (Store store : storeRepository.findAll()) {
			stores.add(new SummaryStore(store));
		}
		return stores;
	}

	@Override
	public void create(Object item) {
		Store store = (Store) item;
		store.setId(((int) storeRepository.count() + 1));
		storeRepository.save(store);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (storeRepository.findById(id).isPresent()) {
			Store store = (Store) item;
			store.setId(id);
			storeRepository.save(store);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (storeRepository.findById(id).isPresent()) {
			storeRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		storeRepository.deleteAll();
	}

//	public Object getProducts(int id) {
//		if (storeRepository.findById(id).isPresent()) {
//			return storeRepository.findById(id).get().getProducts();
//		}
//		return null;
//	}

}
