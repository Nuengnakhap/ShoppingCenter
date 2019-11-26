package com.sop.ShoppingCenter.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Orders;
import com.sop.ShoppingCenter.model.OrderDetail;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.OrderService;

@RestController
public class OrderController implements Controllers {

	@Autowired
	@Qualifier("orderService")
	OrderService orderService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

//	private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	private ObjectMapper mapper = new ObjectMapper();
//	private Customer customer = customerService.getByUsername(auth.getName()).get();

	@Override
	@GetMapping("/order/{id}")
	public Object getById(@PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ObjectMapper mapper = new ObjectMapper();
		Optional<Orders> order = mapper.convertValue(orderService.getById(id), new TypeReference<Optional<Orders>>() {
		});
		if (order.get().getCustomer().getEmail() == auth.getName()) {
			return new ResponseMessage(HttpStatus.OK.value(), order.get());
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@GetMapping("/order")
	public Object getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.getByUsername(auth.getName()).get();
		return orderService.getByCustomer(customer);
	}

	@Override
	@PostMapping("/order")
	public Object create(@RequestBody @Valid Object item) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = customerService.getByUsername(auth.getName()).get();
		List<OrderDetail> details = mapper.convertValue(item, new TypeReference<List<OrderDetail>>() {
		});
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setDetails(details);
		orderService.create(order);
		return orderService.getByCustomer(customer);
	}

	@Override
	@PutMapping("/order/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = mapper.convertValue(item, new TypeReference<Store>() {
		});
		if (orderService.update(id, store)) {
			return new ResponseMessage(HttpStatus.OK.value(), store);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	public Object deleteById(@PathVariable int id) {
		if (orderService.deleteById(id)) {
			return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	public void deleteAll() {
		orderService.deleteAll();
	}
}
