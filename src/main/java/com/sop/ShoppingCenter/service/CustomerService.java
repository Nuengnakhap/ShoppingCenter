package com.sop.ShoppingCenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.repository.CustomerRepository;

@Service("customerService")
public class CustomerService implements Services {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Object getById(int id) {
		if (customerRepository.findById(id).isPresent()) {
			return customerRepository.findById(id);
		}
		return null;
	}

	@Override
	public Object getAll() {
		return customerRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Customer cus = (Customer) item;
		cus.setPassword(new BCryptPasswordEncoder().encode(cus.getPassword()));
		cus.setId(((int) customerRepository.count() + 1));
		customerRepository.save(cus);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (customerRepository.findById(id).isPresent()) {
			Customer cus = (Customer) item;
			cus.setId(id);
			customerRepository.save(cus);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (customerRepository.findById(id).isPresent()) {
			customerRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}
	
	public Boolean getByEmail(String email) {
		return customerRepository.findByEmail(email).isPresent();
	}
	
	public Optional<Customer> getByUsername(String email) {
		return customerRepository.findByEmail(email);
	}

}
