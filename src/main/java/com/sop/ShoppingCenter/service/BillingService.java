package com.sop.ShoppingCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Billing;
import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.repository.BillingRepository;

@Service("billingService")
public class BillingService implements Services {

	@Autowired
	BillingRepository billingRepository;

	@Override
	public Object getById(int id) {
		if (billingRepository.findById(id).isPresent()) {
			return billingRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Object getAll() {
		return billingRepository.findAll();
	}
	
	public List<Billing> getByCustomer(Customer customer) {
		return billingRepository.findByCustomer(customer).get();
	}

	@Override
	public void create(Object item) {
		Billing billing = (Billing) item;
		billingRepository.save(billing);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (billingRepository.findById(id).isPresent()) {
			Billing billing = (Billing) item;
			billing.setId(id);
			billingRepository.save(billing);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (billingRepository.findById(id).isPresent()) {
			billingRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		billingRepository.deleteAll();
	}

}