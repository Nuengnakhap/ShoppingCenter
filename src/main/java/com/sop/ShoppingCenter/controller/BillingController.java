package com.sop.ShoppingCenter.controller;

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
import com.sop.ShoppingCenter.model.Billing;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.BillingService;

@RestController
public class BillingController implements Controllers {
	
	@Autowired
	@Qualifier("billingService")
	BillingService billingService;


	@Override
	@GetMapping("/billing")
	public Object getAll() {
		return billingService.getAll();
	}

	@Override
	@PostMapping("/billing")
	public Object create(@RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Billing billing = mapper.convertValue(item, new TypeReference<Billing>(){});
		billingService.create(billing);
		return billingService.getAll();
	}

	@Override
	@PutMapping("/billing/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Billing billing = mapper.convertValue(item, new TypeReference<Billing>(){});
		if (billingService.update(id, billing)) {
			return new ResponseMessage(HttpStatus.OK.value(), billing);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}
	
	@Override
	@DeleteMapping("/billing/{id}")
	public Object deleteById(int id) {
		if (billingService.deleteById(id)) {
			return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@DeleteMapping("/billing")
	public void deleteAll() {
		billingService.deleteAll();
	}

	@Override
	@GetMapping("/billing/{id}")
	public Object getById(@PathVariable int id) {
		if (billingService.getById(id) != null) {
			return new ResponseMessage(HttpStatus.OK.value(), billingService.getById(id));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}
}
