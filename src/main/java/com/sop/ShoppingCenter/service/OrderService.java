package com.sop.ShoppingCenter.service;


import com.sop.ShoppingCenter.model.CustomerOrder;
import com.sop.ShoppingCenter.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orderService")
public class OrderService implements Services {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Object getById(int id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id);
        }
        return null;
    }

    @Override
    public Object getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void create(Object item) {
        CustomerOrder order = (CustomerOrder) item;
        order.setId(((int) orderRepository.count() + 1));
        orderRepository.save(order);
    }

    @Override
    public Boolean update(int id, Object item) {
        if (orderRepository.findById(id).isPresent()) {
            CustomerOrder order = (CustomerOrder) item;
            order.setId(id);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(int id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }




}
