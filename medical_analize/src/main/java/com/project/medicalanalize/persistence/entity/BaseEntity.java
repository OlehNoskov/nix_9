package com.project.medicalanalize.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Getter
    @Setter
    private Boolean visible;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
        this.visible = true;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = new Date();
    }
}