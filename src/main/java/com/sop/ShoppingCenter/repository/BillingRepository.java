package com.sop.ShoppingCenter.repository;


import com.sop.ShoppingCenter.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {

}