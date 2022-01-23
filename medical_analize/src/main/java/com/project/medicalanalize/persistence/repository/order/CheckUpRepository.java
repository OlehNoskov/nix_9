package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUpRepository extends OrderRepository<CheckUp> {

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='CHECK_UP' and doctor_id =:idDoctor", nativeQuery=true)
    Long countSuccessDoctorCheckUp( @Param("idDoctor") Long idDoctor);

    @Query(value = "select COUNT(*) from orders where order_type='CHECK_UP'", nativeQuery = true)
    Long countAllCheckUp();

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='CHECK_UP'", nativeQuery = true)
    Long countSuccessCheckUp();

    @Query(value = "select COUNT(*) from orders where visible=true and order_type='CHECK_UP'", nativeQuery = true)
    Long countReviewCheckUp();
}