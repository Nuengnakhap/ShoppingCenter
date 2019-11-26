package com.sop.ShoppingCenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Store;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<List<Product>> findByStore(Store store);
}