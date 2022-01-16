package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMPREHENSIVE_CONSULTATION")
public class ConsultationOrder extends Order {

    @Setter
    @Column(name = "price")
    private Integer price;



    public ConsultationOrder() {
        super();
        setOrderType(OrderType.COMPREHENSIVE_CONSULTATION);
    }

    public Integer getPrice() {
        return 12;
    }
}