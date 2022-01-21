package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;
import com.project.medicalanalize.persistence.type.ProphylacticDoses;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TRANSCRIPT")
public class TranscriptOrder extends Order {

    @Setter
    @Column(name = "price")
    private Integer price;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private ProphylacticDoses prophylacticDoses;

    public TranscriptOrder() {
        super();
        setOrderType(OrderType.TRANSCRIPT);
    }

    public Integer getPrice() {
        return 6;
    }
}