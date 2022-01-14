package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TRANSCRIPT")
public class TranscriptOrder extends Order {

    @Column(name = "price")
    private Integer price;

    public TranscriptOrder() {
        super();
        setOrderType(OrderType.TRANSCRIPT);
    }

    public Integer getPrice() {
        return 6;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}