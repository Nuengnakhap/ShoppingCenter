package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sop.ShoppingCenter.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}