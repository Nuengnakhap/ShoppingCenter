package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.service.StoreService;

@RestController
public class StoreController implements Controllers {

	@Autowired
	@Qualifier("storeService")
	StoreService storeService;

	@Override
	@GetMapping("/store/{id}")
	public Object getById(@PathVariable int id) {
		return storeService.getById(id);
	}

	@Override
	@GetMapping("/store")
	public Object getAll() {
		return storeService.getAll();
	}

	@Override
	@PostMapping("/store")
	public Object create(@RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = mapper.convertValue(item, new TypeReference<Store>(){});
		storeService.create(store);
		return storeService.getAll();
	}

	@Override
	@PutMapping("/store/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = mapper.convertValue(item, new TypeReference<Store>(){});
		storeService.update(id, store);
		return storeService.getAll();
	}

	@Override
	@DeleteMapping("/store/{id}")
	public Object deleteById(@PathVariable int id) {
		storeService.deleteById(id);
		return storeService.getAll();
		
	}

	@Override
	@DeleteMapping("/store")
	public void deleteAll() {
		storeService.deleteAll();
	}
}
