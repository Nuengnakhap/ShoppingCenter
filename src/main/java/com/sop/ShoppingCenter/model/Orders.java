package com.sop.ShoppingCenter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orders extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private float total_price;
	private String type = "progress";

    @ManyToOne
    @JoinColumn(nullable = false)
    Customer customer;
    
//    @OneToMany(mappedBy = "order")
//    List<OrderDetail> details;

    public Orders() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//	public List<OrderDetail> getDetails() {
//		return details;
//	}
//
//	public void setDetails(List<OrderDetail> details) {
//		this.details = details;
//	}

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}