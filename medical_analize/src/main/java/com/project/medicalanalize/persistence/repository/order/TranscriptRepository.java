package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query(value = "select * from orders where visible=false and  order_type='TRANSCRIPT'", nativeQuery = true)
    Page<TranscriptOrder> findAllSuccessTranscriptVisibleAdmin(Pageable pageable);

    @Query(value = "select * from orders where visible=true and payment = true and  order_type='TRANSCRIPT'", nativeQuery = true)
    Page<TranscriptOrder> findAllTranscriptVisibleDoctor(Pageable pageable);

    @Query(value = "select * from orders where visible=false and  order_type='TRANSCRIPT'and patient_id =:idPatient", nativeQuery = true)
    Page<TranscriptOrder> findAllSuccessTranscriptPatient(Pageable pageable, @Param("idPatient") Long idPatient);
}