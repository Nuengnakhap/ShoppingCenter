package com.sop.ShoppingCenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	Optional<List<Rating>> findByCustomer(Customer customer);
}