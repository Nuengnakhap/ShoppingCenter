package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;

import com.sop.ShoppingCenter.model.Shopping;
import com.sop.ShoppingCenter.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.response.ResponseMessage;


@RestController
public class ShoppingController implements Controllers {

    @Autowired
    @Qualifier("shoppingService")
    ShoppingService shoppingService;

    @Override
    @GetMapping("/product/{id}")
    public Object getById(@PathVariable int id) {
        if (shoppingService.getById(id) != null) {
            return new ResponseMessage(HttpStatus.OK.value(), shoppingService.getById(id));
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @GetMapping("/product")
    public Object getAll() {
        return new ResponseMessage(HttpStatus.OK.value(), shoppingService.getAll());
    }

    @Override
    @PostMapping("/addproduct")
    public Object create(@RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Shopping shop = mapper.convertValue(item, new TypeReference<Shopping>() {});
        shoppingService.create(shop);
        return new ResponseMessage(HttpStatus.CREATED.value(), shop);
    }

    @Override
    @PutMapping("/updateproduct/{id}")
    public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Shopping shop = mapper.convertValue(item, new TypeReference<Shopping>() {});
        if (shoppingService.update(id, shop)) {
            return new ResponseMessage(HttpStatus.OK.value(), shop);
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteproduct/{id}")
    public Object deleteById(@PathVariable int id) {
        if (shoppingService.deleteById(id)) {
            return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteproduct")
    public void deleteAll() {
        shoppingService.deleteAll();
    }
}