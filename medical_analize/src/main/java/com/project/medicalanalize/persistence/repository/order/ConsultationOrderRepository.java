package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;

import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationOrderRepository extends OrderRepository<ComprehensiveConsultationOrder>{
}