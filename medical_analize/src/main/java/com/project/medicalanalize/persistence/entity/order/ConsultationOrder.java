package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;
import lombok.Getter;
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

    @Getter
    @Setter
    @Column(name = "medicines")
    private String medicines;

    @Getter
    @Setter
    @Column(name = "profile_doctor")
    private String profileDoctor;

    @Getter
    @Setter
    @Column(name = "instrumental_research")
    private String instrumentalResearch;

    @Getter
    @Setter
    @Column(name = "nutritional_advice")
    private String nutritionalAdvice;

    public ConsultationOrder() {
        super();
        setOrderType(OrderType.COMPREHENSIVE_CONSULTATION);
    }

    public Integer getPrice() {
        return 12;
    }
}