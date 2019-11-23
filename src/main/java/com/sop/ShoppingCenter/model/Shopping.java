package com.sop.ShoppingCenter.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class Shopping extends BaseEntity {



    @Id
    private int id;
    private String name;
    private int stock_quantity;
    private float price;
//    private int category_id;
    private ArrayList<String> image = new ArrayList<String>();

    @ManyToOne
    @JoinColumn(nullable = false)
    Store store;

    public Shopping() {
        super();
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
