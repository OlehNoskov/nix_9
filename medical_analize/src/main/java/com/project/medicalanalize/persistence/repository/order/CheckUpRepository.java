package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CheckUpRepository extends OrderRepository<CheckUp> {

//    @Query(value = "select * from orders where visible=true and order_type='CHECK_UP'")
//    Set<TranscriptOrder> findDoctorAllVisibleCheckUp(Set<TranscriptOrder> transcriptOrders);
}