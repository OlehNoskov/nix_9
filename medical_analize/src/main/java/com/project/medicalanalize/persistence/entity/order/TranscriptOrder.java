package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.entity.order.transcript.GeneralBlood;
import com.project.medicalanalize.persistence.type.OrderType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("TRANSCRIPT")
public class TranscriptOrder extends Order {

    @Setter
    @Column(name = "price")
    private Integer price;

    @Getter
    @Setter
    @OneToMany(mappedBy = "transcript", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GeneralBlood> generalBloods;

    public TranscriptOrder() {
        super();
        setOrderType(OrderType.TRANSCRIPT);
        this.generalBloods = new HashSet<>();
    }

    public Integer getPrice() {
        return 6;
    }
}