package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;


import com.sop.ShoppingCenter.model.CustomerOrder;
import com.sop.ShoppingCenter.model.Orderdetail;
import com.sop.ShoppingCenter.service.CustomerService;
import com.sop.ShoppingCenter.service.OrderService;
import com.sop.ShoppingCenter.service.OrderdetailService;
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

import java.security.Principal;
import java.util.List;


@RestController
public class OrderController implements Controllers {

    @Autowired
    @Qualifier("orderService")
    OrderService orderService;
    CustomerService customerService;
    Principal principal;
    OrderdetailService orderdetailService;
    ShoppingService shoppingService;

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
    @PostMapping("/order")
    public Object create(@RequestBody @Valid Object item) {

        CustomerOrder cs = new CustomerOrder();
        cs.setCustomer(customerService.getByUsername(principal.getName()));
        orderService.create(cs);
        ObjectMapper mapper = new ObjectMapper();
        List<Orderdetail> orderdetail = mapper.convertValue(item, new TypeReference<Orderdetail>() {});
        for(int i = 0; i < orderdetail.size(); i++) {
            Orderdetail ot = new Orderdetail();
            ot = orderdetail.get(i);
            int count = orderService.getCount();
            ot.setCustomerOrder((CustomerOrder) orderService.getById(count));
            orderdetailService.create(ot);
        }

        return new ResponseMessage(HttpStatus.CREATED.value(), cs);
    }

    @Override
    @PutMapping("/order/{id}")
    public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerOrder order = mapper.convertValue(item, new TypeReference<CustomerOrder>() {});
        if (orderService.update(id, order)) {
            return new ResponseMessage(HttpStatus.OK.value(), order);
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/order/{id}")
    public Object deleteById(@PathVariable int id) {
        if (orderService.deleteById(id)) {
            return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
        }
        return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
    }

    @Override
    @DeleteMapping("/order")
    public void deleteAll() {
        orderService.deleteAll();
    }
}
