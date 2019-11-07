package com.sop.ShoppingCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sop.ShoppingCenter.model.Billing;
import com.sop.ShoppingCenter.repository.BillingRepository;

@Service
public class BillingService implements Services {
	
	@Autowired
	BillingRepository billingRepository;
	
	@Override
	public Object getById(int id) {
		if (billingRepository.findById(id).isPresent()) {
			return billingRepository.findById(id);
		}
		return null;
	}

	@Override
	public Object getAll() {
		return billingRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Billing billing = (Billing) item;
		billing.setId(((int) billingRepository.count() + 1));
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
