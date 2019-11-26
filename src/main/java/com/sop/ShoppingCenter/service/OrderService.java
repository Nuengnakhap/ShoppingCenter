package com.sop.ShoppingCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Orders;
import com.sop.ShoppingCenter.repository.OrderRepository;

@Service("orderService")
public class OrderService implements Services {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Object getById(int id) {
		try {
			if (orderRepository.findById(id).isPresent()) {
				return orderRepository.findById(id).get();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object getAll() {
		return orderRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Orders order = (Orders) item;
		orderRepository.save(order);
	}

	public Orders create(Orders order) {
		return orderRepository.save(order);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (orderRepository.findById(id).isPresent()) {
			Orders order = (Orders) item;
			order.setId(id);
			orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (orderRepository.findById(id).isPresent()) {
			orderRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	public List<Orders> getByCustomer(Customer cus) {
		if (orderRepository.findByCustomer(cus).isPresent()) {
			return orderRepository.findByCustomer(cus).get();
		}
		return null;
	}

}
