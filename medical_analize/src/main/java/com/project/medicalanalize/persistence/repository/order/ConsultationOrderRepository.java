package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConsultationOrderRepository extends OrderRepository<ConsultationOrder>{

//    @Query(value = "select * from orders where visible=true and order_type='COMPREHENSIVE_CONSULTATION'")
//    Set<TranscriptOrder> findDoctorAllVisibleConsultation(Set<TranscriptOrder> transcriptOrders);
}