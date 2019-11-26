package com.sop.ShoppingCenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.OrderDetail;
import com.sop.ShoppingCenter.model.Orders;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	Optional<List<OrderDetail>> findByOrder(Orders order);
}