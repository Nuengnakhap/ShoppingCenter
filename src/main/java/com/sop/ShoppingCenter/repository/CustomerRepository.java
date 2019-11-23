package com.sop.ShoppingCenter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT t FROM Customer t where t.email = :email") 
	Optional<Customer> findByEmail(@Param("email") String email);
//	Customer findByEmail(String email);
}
