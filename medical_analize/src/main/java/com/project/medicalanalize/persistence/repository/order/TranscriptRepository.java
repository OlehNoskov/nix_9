package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends OrderRepository<TranscriptOrder> {
}