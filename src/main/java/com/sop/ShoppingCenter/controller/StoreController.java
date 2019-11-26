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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Image;
import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.ImageService;
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
	@Qualifier("imageService")
	ImageService imageService;

	@Override
	@GetMapping("/store/{id}")
	public Object getById(@PathVariable int id) {
		if (storeService.getById(id) != null) {
			return new ResponseMessage(HttpStatus.OK.value(), storeService.getById(id));
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
			return new ResponseMessage(HttpStatus.OK.value(), storeService.getProducts(id));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

//	@PostMapping("/store/{id}/product")
	@RequestMapping(value = "/store/{id}/product", method = RequestMethod.POST, consumes = "application/json")
	public Object createProductByStore(@PathVariable int id, @RequestBody @Valid List<Product> item) {
		if (storeService.getById(id) != null) {
			ObjectMapper mapper = new ObjectMapper();
//			List<Product> products = mapper.convertValue(item, new TypeReference<List<Product>>() {});
//			System.out.println(item.get(0).getImages().get(0));
			for (int i = 0; i < item.size(); i++) {
//				List<Image> lst_img = new ArrayList<Image>();
				for (int j = 0; j < item.get(i).getImages().size(); j++) {
					Image img = new Image();
					img.setImage(item.get(i).getImages().get(j));
					img.setProduct(item.get(i));
					imageService.create(img);
//					lst_img.add(img);
				}
//				item.get(i).setImages(lst_img);
			}
			
//			Store store = mapper.convertValue(storeService.getById(id), new TypeReference<List<Store>>() {});
//			store.setProducts(item);
//			storeService.update(id, store);
			return new ResponseMessage(HttpStatus.OK.value(), storeService.getProducts(id));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

}
