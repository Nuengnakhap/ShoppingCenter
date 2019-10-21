package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

}
