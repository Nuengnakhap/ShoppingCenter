package com.sop.ShoppingCenter.service;

import com.sop.ShoppingCenter.model.Shopping;
import com.sop.ShoppingCenter.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("shoppingService")
public class ShoppingService implements Services {

    @Autowired
    ShoppingRepository shoppingrepository;

    @Override
    public Object getById(int id) {
        if (shoppingrepository.findById(id).isPresent()) {
            return shoppingrepository.findById(id);
        }
        return null;
    }

    @Override
    public Object getAll() {
        return shoppingrepository.findAll();
    }

    @Override
    public void create(Object item) {
        Shopping shop = (Shopping) item;
        shop.setId(((int) shoppingrepository.count() + 1));
        shoppingrepository.save(shop);
    }

    @Override
    public Boolean update(int id, Object item) {
        if (shoppingrepository.findById(id).isPresent()) {
            Shopping shop = (Shopping) item;
            shop.setId(id);
            shoppingrepository.save(shop);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteById(int id) {
        if (shoppingrepository.findById(id).isPresent()) {
            shoppingrepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        shoppingrepository.deleteAll();
    }




}
