package com.sop.ShoppingCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sop.ShoppingCenter.model.Rating;
import com.sop.ShoppingCenter.repository.RatingReposity;

@Service
public class RatingService implements Services{

    @Autowired
    RatingReposity ratingRepository;

    @Override
    public Object getById(int id) {
        if (ratingRepository.findById(id).isPresent()) {
            return ratingRepository.findById(id);
        }
        return null;
    }

    @Override
    public Object getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void create(Object item) {
        Rating rating = (Rating) item;
        rating.setId(((int) ratingRepository.count() + 1));
        ratingRepository.save(rating);
    }

    @Override
    public Boolean update(int id, Object item) {
        if (ratingRepository.findById(id).isPresent()) {
            Rating rating = (Rating) item;
            rating.setId(id);
            ratingRepository.save(rating);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(int id) {
        if (ratingRepository.findById(id).isPresent()) {
            ratingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        ratingRepository.deleteAll();
    }

}
