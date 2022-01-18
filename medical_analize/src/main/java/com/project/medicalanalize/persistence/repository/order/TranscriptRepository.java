package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TranscriptRepository extends OrderRepository<TranscriptOrder> {

//    @Query(value = "select * from orders where visible=true and order_type='TRANSCRIPT'")
//    Set<TranscriptOrder> findDoctorAllVisibleTranscript(Set<TranscriptOrder> transcriptOrders);

}