package com.sop.ShoppingCenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Billing;
import com.sop.ShoppingCenter.model.Customer;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {
	Optional<List<Billing>> findByCustomer(Customer customer);
}