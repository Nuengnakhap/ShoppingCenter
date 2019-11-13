package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;


import com.sop.ShoppingCenter.model.Orderdetail;
import com.sop.ShoppingCenter.service.OrderdetailService;
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
public class OrderdetailController implements Controllers {

    @Autowired
    @Qualifier("orderdetailService")
    OrderdetailService orderdetailService;

    @Override
    @GetMapping("/orderdetail/{id}")
    public Object getById(@PathVariable int id) {
        if (orderdetailService.getById(id) != null) {
            return new ResponseMessage(HttpStatus.OK.value(), orderdetailService.getById(id));
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @GetMapping("/orderdetail")
    public Object getAll() {
        return new ResponseMessage(HttpStatus.OK.value(), orderdetailService.getAll());
    }

    @Override
    @PostMapping("/addorderdetail")
    public Object create(@RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Orderdetail orderdetail = mapper.convertValue(item, new TypeReference<Orderdetail>() {});
        orderdetailService.create(orderdetail);
        return new ResponseMessage(HttpStatus.CREATED.value(), orderdetail);
    }

    @Override
    @PutMapping("/updateorderdetail/{id}")
    public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        Orderdetail orderdetail = mapper.convertValue(item, new TypeReference<Orderdetail>() {});
        if (orderdetailService.update(id, orderdetail)) {
            return new ResponseMessage(HttpStatus.OK.value(), orderdetail);
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteorderdetail/{id}")
    public Object deleteById(@PathVariable int id) {
        if (orderdetailService.deleteById(id)) {
            return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteorderdetail")
    public void deleteAll() {
        orderdetailService.deleteAll();
    }
}