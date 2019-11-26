package com.sop.ShoppingCenter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Category;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.model.SummaryProduct;
import com.sop.ShoppingCenter.model.SummaryStore;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CategoryService;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.ProductService;
import com.sop.ShoppingCenter.service.StoreService;

@RestController
public class StoreController implements Controllers {

	@Autowired
	@Qualifier("storeService")
	StoreService storeService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Autowired
	@Qualifier("productService")
	ProductService productService;

	@Autowired
	@Qualifier("categoryService")
	CategoryService categoryService;

	@Override
	@GetMapping("/store/{id}")
	public Object getById(@PathVariable int id) {
		if (storeService.getById(id) != null) {
			return new ResponseMessage(HttpStatus.OK.value(), new SummaryStore((Store) storeService.getById(id)));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@GetMapping("/store")
	public Object getAll() {
		return storeService.getAll();
	}

	@Override
	@PostMapping("/store")
	public Object create(@RequestBody @Valid Object item) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.getByUsername(auth.getName()).get();
		ObjectMapper mapper = new ObjectMapper();
		Store store = mapper.convertValue(item, new TypeReference<Store>() {
		});
		store.setCustomer(customer);
		storeService.create(store);
		return storeService.getAll();
	}

	@Override
	@PutMapping("/store/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Store store = mapper.convertValue(item, new TypeReference<Store>() {
		});
		if (storeService.update(id, store)) {
			return new ResponseMessage(HttpStatus.OK.value(), store);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@DeleteMapping("/store/{id}")
	public Object deleteById(@PathVariable int id) {
		if (storeService.deleteById(id)) {
			return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@DeleteMapping("/store")
	public void deleteAll() {
		storeService.deleteAll();
	}

	@GetMapping("/store/{id}/product")
	public Object getProductByStore(@PathVariable int id) {
		if (storeService.getById(id) != null) {
			List<SummaryProduct> products = new ArrayList<SummaryProduct>();
			for (Product product : productService.getByStore((Store) storeService.getById(id))) {
				products.add(new SummaryProduct(product));
			}
			return new ResponseMessage(HttpStatus.OK.value(), products);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@PostMapping(path = "/store/{id}/product", consumes = "application/json")
	public Object createProductByStore(@PathVariable int id, @RequestBody @Valid Object item) {
		if (storeService.getById(id) != null) {
			ObjectMapper mapper = new ObjectMapper();
			Store store = mapper.convertValue(storeService.getById(id), new TypeReference<Store>() {
			});
			List<Product> products = mapper.convertValue(item, new TypeReference<List<Product>>() {
			});
			for (int i = 0; i < products.size(); i++) {
				Category category = (Category) categoryService.getById(products.get(i).getCategory_id());
				products.get(i).setCategory(category);
				products.get(i).setStore(store);
				Product product = productService.create(products.get(i));
				products.set(i, product);
			}
			storeService.update(id, store);
			return new ResponseMessage(HttpStatus.OK.value(), store);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@PutMapping("/store/{id}/product/{pid}")
	public Object updateProduct(@PathVariable int id, @PathVariable int pid, @RequestBody @Valid Product item) {
		if (storeService.getById(id) != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = customerService.getByUsername(auth.getName()).get();
			ObjectMapper mapper = new ObjectMapper();
			Store store = mapper.convertValue(storeService.getById(id), new TypeReference<Store>() {
			});
			if (store.getCustomer().getId() == customer.getId()) {
				Category category = (Category) categoryService.getById(item.getCategory_id());
				item.setCategory(category);
				item.setStore(store);
				if (productService.update(pid, item)) {
					return new ResponseMessage(HttpStatus.OK.value(), item);
				}
			}
			return new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "Not has permission");
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}
}
