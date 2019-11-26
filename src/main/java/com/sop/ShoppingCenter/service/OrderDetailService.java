package com.sop.ShoppingCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.OrderDetail;
import com.sop.ShoppingCenter.model.Orders;
import com.sop.ShoppingCenter.repository.OrderDetailRepository;

@Service("orderDetailService")
public class OrderDetailService implements Services {

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public Object getById(int id) {
		if (orderDetailRepository.findById(id).isPresent()) {
			return orderDetailRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Object getAll() {
		return orderDetailRepository.findAll();
	}

	public List<OrderDetail> getByOrder(Orders order) {
		if (orderDetailRepository.findByOrder(order).isPresent()) {
			return orderDetailRepository.findByOrder(order).get();
		}
		return null;
	}

	@Override
	public void create(Object item) {
		OrderDetail detail = (OrderDetail) item;
		detail.setId(((int) orderDetailRepository.count() + 1));
		orderDetailRepository.save(detail);
	}

	public void createMany(List<OrderDetail> item) {
		for (OrderDetail orderDetail : item) {
			orderDetailRepository.save(orderDetail);
		}
	}

	@Override
	public Boolean update(int id, Object item) {
		if (orderDetailRepository.findById(id).isPresent()) {
			OrderDetail detail = (OrderDetail) item;
			detail.setId(id);
			orderDetailRepository.save(detail);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (orderDetailRepository.findById(id).isPresent()) {
			orderDetailRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}
}