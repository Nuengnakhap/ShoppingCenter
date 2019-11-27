package com.sop.ShoppingCenter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Billing;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Orders;
import com.sop.ShoppingCenter.model.SummaryBilling;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.BillingService;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.OrderService;

@RestController
public class BillingController implements Controllers {

	@Autowired
	@Qualifier("billingService")
	BillingService billingService;

	@Autowired
	@Qualifier("orderService")
	OrderService orderService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Override
	@GetMapping("/billing/{id}")
	public Object getById(@PathVariable int id) {
		try {
			Billing billing = (Billing) billingService.getById(id);
			if (!billing.equals(null)) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (billing.getCustomer().getEmail().equalsIgnoreCase(auth.getName())) {
					return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(),
							new SummaryBilling((Billing) billingService.getById(id))));
				}
				return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
						.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "You don't have permission"));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
		}

	}

	@Override
	@GetMapping("/billing")
	public Object getAll() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = customerService.getByUsername(auth.getName()).get();
			List<SummaryBilling> billings = new ArrayList<SummaryBilling>();
			for (Billing billing : billingService.getByCustomer(customer)) {
				billings.add(new SummaryBilling(billing));
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(), billings));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
		}
	}

	@Override
	@PostMapping("/billing")
	public Object create(@RequestBody @Valid Object item) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			ObjectMapper mapper = new ObjectMapper();
			Billing billing = mapper.convertValue(item, new TypeReference<Billing>() {
			});

			Orders order = (Orders) orderService.getById(billing.getOrder_id());
			if (order.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			if (order.getCustomer().getEmail().equalsIgnoreCase(auth.getName())) {
				if (order.getType().equalsIgnoreCase("success") || order.getType().equalsIgnoreCase("closed")) {
					return ResponseEntity.status(HttpStatus.CONFLICT).body(
							new ResponseMessage(HttpStatus.CONFLICT.value(), "This order has been canceled or paid!"));
				}
				order.setType("success");
				billing.setOrder(order);
				billing.setCustomer(order.getCustomer());
				billingService.create(billing);
				orderService.update(order.getId(), order);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED.value(),
						"Bill has been created"));
			}
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "You don't have permission!"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Can't create billing!"));
		}
	}

	@Override
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		return null;
	}

	@Override
	public Object deleteById(@PathVariable int id) {
		return null;
	}

	@Override
	public void deleteAll() {
	}
}