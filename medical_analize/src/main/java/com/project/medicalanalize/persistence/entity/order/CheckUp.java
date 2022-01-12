package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHECK_UP")
public class CheckUp extends Order {

    @Column(name = "price")
    private Integer price;

    public CheckUp() {
        super();
        setOrderType(OrderType.CHECK_UP);
    }

    public Integer getPrice() {
        return 8;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}