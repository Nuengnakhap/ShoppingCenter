package com.sop.ShoppingCenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sop.ShoppingCenter.model.Customer;
import com.sop.ShoppingCenter.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	Optional<List<Orders>> findByCustomer(Customer customer);
}