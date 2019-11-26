package com.sop.ShoppingCenter.controller;


import javax.validation.Valid;
import com.sop.ShoppingCenter.model.*;
import com.sop.ShoppingCenter.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.response.ResponseMessage;

@RestController
public class RatingController implements Controllers {

    @Autowired
    @Qualifier("ratingService")
    RatingService ratingService;


    @Override
    @GetMapping("/rating/{id}")
    public Object getById(@PathVariable int id) {
        if (ratingService.getById(id) != null) {
            return new ResponseMessage(HttpStatus.OK.value(), ratingService.getById(id));
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @GetMapping("/rating")
    public Object getAll() {
        return new ResponseMessage(HttpStatus.OK.value(), ratingService.getAll());
    }

    @Override
    @PostMapping("/rating")
    public Object create(@RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Rating rating = mapper.convertValue(item, new TypeReference<Rating>() {});
        ratingService.create(rating);
        return new ResponseMessage(HttpStatus.CREATED.value(), rating);
    }

    @Override
    @PutMapping("/rating/{id}")
    public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Rating rating = mapper.convertValue(item, new TypeReference<Rating>() {});
        if (ratingService.update(id, rating)) {
            return new ResponseMessage(HttpStatus.OK.value(), rating);
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/rating/{id}")
    public Object deleteById(@PathVariable int id) {
        if (ratingService.deleteById(id)) {
            return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/rating")
    public void deleteAll() {
        ratingService.deleteAll();
    }
}