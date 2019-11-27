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
import com.sop.ShoppingCenter.model.OrderDetail;
import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Rating;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.model.SummaryRating;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.BillingService;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.OrderDetailService;
import com.sop.ShoppingCenter.service.ProductService;
import com.sop.ShoppingCenter.service.RatingService;
import com.sop.ShoppingCenter.service.StoreService;

@RestController
public class RatingController implements Controllers {

	@Autowired
	@Qualifier("ratingService")
	RatingService ratingService;

	@Autowired
	@Qualifier("billingService")
	BillingService billingService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Autowired
	@Qualifier("productService")
	ProductService productService;

	@Autowired
	@Qualifier("orderDetailService")
	OrderDetailService orderDetailService;

	@Autowired
	@Qualifier("storeService")
	StoreService storeService;

	@Override
	@GetMapping("/rating/{id}")
	public Object getById(@PathVariable int id) {
		try {
			Rating rating = (Rating) ratingService.getById(id);
			if (!rating.equals(null)) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (rating.getCustomer().getEmail().equalsIgnoreCase(auth.getName())) {
					return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(),
							new SummaryRating(rating)));
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
	@GetMapping("/rating")
	public Object getAll() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = customerService.getByUsername(auth.getName()).get();
			ratingService.getByCustomer(customer);
			List<SummaryRating> ratings = new ArrayList<SummaryRating>();
			for (Rating rating : ratingService.getByCustomer(customer)) {
				ratings.add(new SummaryRating(rating));
			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(), ratings));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
		}
	}

	@Override
	@PostMapping("/rating")
	public Object create(@RequestBody @Valid Object item) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			ObjectMapper mapper = new ObjectMapper();
			Rating rating = mapper.convertValue(item, new TypeReference<Rating>() {
			});
			
			if (rating.getStars() > 5) {
				return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
						.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "Do not allow more than 5 stars!"));
			}

			Billing billing = (Billing) billingService.getById(rating.getBilling_id());

			if (billing.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			if (!billing.getCustomer().getEmail().equalsIgnoreCase(auth.getName())) {
				return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
						.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "You don't have permission!"));
			}

			OrderDetail detail = (OrderDetail) orderDetailService.getById(rating.getOrderDetail_id());
			if (detail.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			if (detail.isRated()) {
				return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ResponseMessage(
						HttpStatus.METHOD_NOT_ALLOWED.value(), "You have already rated this order!"));
			}
			detail.setRated(true);
			orderDetailService.update(detail.getId(), detail);

			rating.setBilling(billing);
			rating.setOrderDetail(detail);
			rating.setCustomer(billing.getCustomer());
			ratingService.create(rating);

			Product product = (Product) productService.getById(detail.getProduct().getId());
			int people = product.getPeople_stars() + 1;
			product.setPeople_stars(people);
			float stars = ((product.getStars() * product.getPeople_stars()) + rating.getStars()) / people;
			product.setStars(stars);
			productService.update(product.getId(), product);

			Store store = (Store) storeService.getById(product.getStore().getId());
			int storeRate = people + store.getPeople_stars();
			store.setPeople_stars(storeRate);
			store.setStars(((store.getStars() * store.getPeople_stars()) + (stars * people)) / (storeRate));
			storeService.update(store.getId(), store);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseMessage(HttpStatus.CREATED.value(), "Data has been created!"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Can't create rating!"));
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