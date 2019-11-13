package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CustomerOrder extends BaseEntity {

    @Id
    private int id;
    private float total_price;

    @ManyToOne
    @JoinColumn(nullable = false)
    Customer customer;


    public CustomerOrder() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }



}

