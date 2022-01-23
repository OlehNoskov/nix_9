package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationOrderRepository extends OrderRepository<ConsultationOrder>{

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='COMPREHENSIVE_CONSULTATION' and doctor_id =:idDoctor", nativeQuery=true)
    Long countSuccessDoctorConsultation( @Param("idDoctor") Long idDoctor);

    @Query(value = "select COUNT(*) from orders where order_type='COMPREHENSIVE_CONSULTATION'", nativeQuery = true)
    Long countAllConsultation();

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='COMPREHENSIVE_CONSULTATION'", nativeQuery = true)
    Long countSuccessConsultation();

    @Query(value = "select COUNT(*) from orders where visible=true and order_type='COMPREHENSIVE_CONSULTATION'", nativeQuery = true)
    Long countReviewConsultation();
}