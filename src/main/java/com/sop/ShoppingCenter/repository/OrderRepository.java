package com.sop.ShoppingCenter.repository;

import com.sop.ShoppingCenter.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {

}
