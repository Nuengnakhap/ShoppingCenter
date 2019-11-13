package com.sop.ShoppingCenter.repository;

import com.sop.ShoppingCenter.model.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderdetailRepository extends JpaRepository<Orderdetail, Integer> {

}