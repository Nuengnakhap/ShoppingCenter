package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orderdetail extends BaseEntity {

    @Id
    private int id;
    private int quantity;
    private float price;

    @ManyToOne
    @JoinColumn(nullable = false)
    Shopping shopping;

    @ManyToOne
    @JoinColumn(nullable = false)
    CustomerOrder customerOrder;


    public Orderdetail() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
