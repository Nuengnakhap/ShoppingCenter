package com.sop.ShoppingCenter.service;


import com.sop.ShoppingCenter.model.Orderdetail;
import com.sop.ShoppingCenter.repository.OrderdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orderdetailService")
public class OrderdetailService implements Services {

    @Autowired
    OrderdetailRepository orderdetailRepository;

    @Override
    public Object getById(int id) {
        if (orderdetailRepository.findById(id).isPresent()) {
            return orderdetailRepository.findById(id);
        }
        return null;
    }

    @Override
    public Object getAll() {
        return orderdetailRepository.findAll();
    }

    @Override
    public void create(Object item) {
        Orderdetail orderdetail = (Orderdetail) item;
        orderdetail.setId(((int) orderdetailRepository.count() + 1));
        orderdetailRepository.save(orderdetail);
    }

    @Override
    public Boolean update(int id, Object item) {
        if (orderdetailRepository.findById(id).isPresent()) {
            Orderdetail orderdetail = (Orderdetail) item;
            orderdetail.setId(id);
            orderdetailRepository.save(orderdetail);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(int id) {
        if (orderdetailRepository.findById(id).isPresent()) {
            orderdetailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        orderdetailRepository.deleteAll();
    }




}