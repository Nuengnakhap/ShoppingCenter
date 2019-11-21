package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sop.ShoppingCenter.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
