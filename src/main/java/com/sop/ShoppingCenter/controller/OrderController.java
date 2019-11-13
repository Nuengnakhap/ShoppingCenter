package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;


import com.sop.ShoppingCenter.model.CustomerOrder;
import com.sop.ShoppingCenter.service.OrderService;
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
public class OrderController implements Controllers {

    @Autowired
    @Qualifier("orderService")
    OrderService orderService;

    @Override
    @GetMapping("/order/{id}")
    public Object getById(@PathVariable int id) {
        if (orderService.getById(id) != null) {
            return new ResponseMessage(HttpStatus.OK.value(), orderService.getById(id));
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @GetMapping("/order")
    public Object getAll() {
        return new ResponseMessage(HttpStatus.OK.value(), orderService.getAll());
    }

    @Override
    @PostMapping("/addorder")
    public Object create(@RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerOrder order = mapper.convertValue(item, new TypeReference<CustomerOrder>() {});
        orderService.create(order);
        return new ResponseMessage(HttpStatus.CREATED.value(), order);
    }

    @Override
    @PutMapping("/updateorder/{id}")
    public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerOrder order = mapper.convertValue(item, new TypeReference<CustomerOrder>() {});
        if (orderService.update(id, order)) {
            return new ResponseMessage(HttpStatus.OK.value(), order);
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteorder/{id}")
    public Object deleteById(@PathVariable int id) {
        if (orderService.deleteById(id)) {
            return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/deleteorder")
    public void deleteAll() {
        orderService.deleteAll();
    }
}
