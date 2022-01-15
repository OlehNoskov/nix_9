package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;

import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TRANSCRIPT")
public class TranscriptOrder extends Order {

    @Setter
    @Column(name = "price")
    private Integer price;

    public TranscriptOrder() {
        super();
        setOrderType(OrderType.TRANSCRIPT);
    }

    public Integer getPrice() {
        return 6;
    }
}