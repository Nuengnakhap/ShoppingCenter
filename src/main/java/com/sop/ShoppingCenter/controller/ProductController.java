package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.ProductService;

@RestController("product")
public class ProductController implements Controllers {

	@Autowired
	@Qualifier("productService")
	ProductService productService;

	@Override
	@GetMapping("/{id}")
	public Object getById(@PathVariable int id) {
		if (productService.getById(id) != null) {
			return new ResponseMessage(HttpStatus.OK.value(), productService.getById(id));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@GetMapping("/")
	public Object getAll() {
		return new ResponseMessage(HttpStatus.OK.value(), productService.getAll());
	}
	
	@Override
	public Object create(@RequestBody @Valid Object item) {
		return item;
	}

	@Override
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		return item;
	}

	@Override
	public Object deleteById(@PathVariable int id) {
		return id;
	}

	@Override
	public void deleteAll() {
	}
}
