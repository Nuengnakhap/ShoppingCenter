package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Rating extends BaseEntity{

    @Id
    private int id;

    public Rating(){
        super();
    }
    public int detId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
