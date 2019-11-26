package com.sop.ShoppingCenter.repository;

import com.sop.ShoppingCenter.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
