package com.sop.ShoppingCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sop.ShoppingCenter.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}