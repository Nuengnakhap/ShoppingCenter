package com.sop.ShoppingCenter.repository;
;

import com.sop.ShoppingCenter.model.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Integer> {
}
