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
import com.sop.ShoppingCenter.model.OrderDetail;
import com.sop.ShoppingCenter.model.Orders;
import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.model.SummaryOrderDetail;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.OrderDetailService;
import com.sop.ShoppingCenter.service.OrderService;
import com.sop.ShoppingCenter.service.ProductService;

@RestController
public class OrderController implements Controllers {

	@Autowired
	@Qualifier("orderService")
	OrderService orderService;

	@Autowired
	@Qualifier("orderDetailService")
	OrderDetailService orderDetailService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Autowired
	@Qualifier("productService")
	ProductService productService;

//	private Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	private ObjectMapper mapper = new ObjectMapper();
//	private Customer customer = customerService.getByUsername(auth.getName()).get();

	@Override
	@GetMapping("/order/{id}")
	public Object getById(@PathVariable int id) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Orders order = (Orders) orderService.getById(id);
			if (order.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			List<SummaryOrderDetail> details = new ArrayList<SummaryOrderDetail>();
			for (OrderDetail detail : orderDetailService.getByOrder(order)) {
				details.add(new SummaryOrderDetail(detail));
			}
			if (order.getCustomer().getEmail().equalsIgnoreCase(auth.getName())) {
				return new ResponseMessage(HttpStatus.OK.value(), details);
			}
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "You don't have permission!"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
		}
	}

	@Override
	@GetMapping("/order")
	public Object getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.getByUsername(auth.getName()).get();
		return new ResponseMessage(HttpStatus.OK.value(), orderService.getByCustomer(customer));
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
		order = orderService.create(order);

		float total_price = 0;
		List<Product> products = new ArrayList<Product>();
		for (OrderDetail orderDetail : details) {
			Product product = (Product) productService.getById(orderDetail.getProduct_id());
			if (product.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			orderDetail.setPrice(product.getPrice() * orderDetail.getQuantity());
			total_price += orderDetail.getPrice();

			int stock = product.getStock_quantity() - orderDetail.getQuantity();
			if (stock < 0) {
				orderService.deleteById(order.getId());
				return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
						.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "The number of products is not enough!"));
			}
			product.setStock_quantity(stock);
			products.add(product);
		}

		order.setTotal_price(total_price);
		if (orderService.update(order.getId(), order)) {
			orderDetailService.createMany(details);
			productService.updateMany(products);
			return new ResponseMessage(HttpStatus.OK.value(), orderService.getByCustomer(customer));
		}
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "Cannot create order!"));
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
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
	}

	@Override
	@DeleteMapping("/order/{id}")
	public Object deleteById(@PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		ObjectMapper mapper = new ObjectMapper();
		Customer customer = customerService.getByUsername(auth.getName()).get();
		Orders order = (Orders) orderService.getById(id);
		
		if (order.equals(null)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
		}
		if (!order.getCustomer().getEmail().equalsIgnoreCase(customer.getEmail())) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(new ResponseMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), "You don't have permission!"));
		}
		
		List<Product> products = new ArrayList<Product>();
		List<OrderDetail> details = orderDetailService.getByOrder(order);
		for (OrderDetail orderDetail : details) {
			Product product = (Product) productService.getById(orderDetail.getProduct().getId());
			if (product.equals(null)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
			}
			int stock = product.getStock_quantity() + orderDetail.getQuantity();
			product.setStock_quantity(stock);
			products.add(product);
		}
		order.setType("closed");
		
		if (orderService.update(order.getId(), order)) {
			productService.updateMany(products);
			return new ResponseMessage(HttpStatus.OK.value(), orderService.getByCustomer(customer));
		}
		
//		if (orderService.deleteById(id)) {
//			return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
//		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found"));
	}

	@Override
	public void deleteAll() {
		orderService.deleteAll();
	}
}
