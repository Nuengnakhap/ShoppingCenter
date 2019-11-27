package com.sop.ShoppingCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Rating;
import com.sop.ShoppingCenter.repository.RatingRepository;

@Service("ratingService")
public class RatingService implements Services {

	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Object getById(int id) {
		if (ratingRepository.findById(id).isPresent()) {
			return ratingRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Object getAll() {
		return ratingRepository.findAll();
	}
	
	public List<Rating> getByCustomer(Customer customer) {
		return ratingRepository.findByCustomer(customer).get();
	}

	@Override
	public void create(Object item) {
		Rating rating = (Rating) item;
		ratingRepository.save(rating);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (ratingRepository.findById(id).isPresent()) {
			Rating rating = (Rating) item;
			rating.setId(id);
			ratingRepository.save(rating);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (ratingRepository.findById(id).isPresent()) {
			ratingRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		ratingRepository.deleteAll();
	}

}