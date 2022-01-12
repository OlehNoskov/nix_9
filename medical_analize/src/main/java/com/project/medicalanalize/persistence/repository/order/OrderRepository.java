package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.repository.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository <O extends Order> extends BaseRepository<O> {
}