package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sop.ShoppingCenter.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}