package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TranscriptRepository extends OrderRepository<TranscriptOrder> {

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='TRANSCRIPT' and doctor_id =:idDoctor", nativeQuery = true)
    Long countSuccessDoctorTranscript(@Param("idDoctor") Long idDoctor);

    @Query(value = "select COUNT(*) from orders where order_type='TRANSCRIPT'", nativeQuery = true)
    Long countAllTranscript();

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='TRANSCRIPT'", nativeQuery = true)
    Long countSuccessTranscript();

    @Query(value = "select COUNT(*) from orders where visible=true and order_type='TRANSCRIPT'", nativeQuery = true)
    Long countReviewTranscript();

//    @Query(value = "select * from orders where where visible=false and  order_type='TRANSCRIPT'", nativeQuery = true)
//    List<TranscriptOrder> findAllSuccessTranscript();
}