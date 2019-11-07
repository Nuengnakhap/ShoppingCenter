package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Billing;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {

}
