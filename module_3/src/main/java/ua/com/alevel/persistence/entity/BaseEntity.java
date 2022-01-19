package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date updated;

    public BaseEntity() {
        this.created = created;
        this.updated = updated;
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = new Date();
    }
}