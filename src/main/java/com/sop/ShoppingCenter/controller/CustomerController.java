package com.sop.ShoppingCenter.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CustomerService;

@RestController
public class CustomerController implements Controllers {

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Override
	@GetMapping("/customer/{id}")
	public Object getById(@PathVariable int id) {
		return customerService.getById(id);
	}

	@Override
	@GetMapping("/customer")
	public Object getAll() {
		return customerService.getAll();
	}

	@Override
	@PostMapping("/customer")
	public Object create(@RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Customer cus = mapper.convertValue(item, new TypeReference<Customer>() {});
		if (customerService.getByEmail(cus.getEmail())) {
			return new ResponseMessage(new Date(), HttpStatus.CONFLICT.value(), "Email has already taken");
		} else {
			customerService.create(cus);
			return new ResponseMessage(new Date(), HttpStatus.CREATED.value(), cus);
		}
	}

	@Override
	@PutMapping("/customer/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Customer cus = mapper.convertValue(item, new TypeReference<Customer>() {
		});
		customerService.update(id, cus);
		return customerService.getAll();
	}

	@Override
	@DeleteMapping("/customer/{id}")
	public Object deleteById(@PathVariable int id) {
		customerService.deleteById(id);
		return customerService.getAll();

	}

	@Override
	@DeleteMapping("/customer")
	public void deleteAll() {
		customerService.deleteAll();
	}
}
