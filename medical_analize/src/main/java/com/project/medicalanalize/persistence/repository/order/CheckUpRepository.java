package com.project.medicalanalize.persistence.repository.order;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckUpRepository extends OrderRepository<CheckUp> {

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='CHECK_UP' and doctor_id =:idDoctor", nativeQuery = true)
    Long countSuccessDoctorCheckUp(@Param("idDoctor") Long idDoctor);

    @Query(value = "select COUNT(*) from orders where order_type='CHECK_UP'", nativeQuery = true)
    Long countAllCheckUp();

    @Query(value = "select COUNT(*) from orders where visible=false and order_type='CHECK_UP'", nativeQuery = true)
    Long countSuccessCheckUp();

    @Query(value = "select COUNT(*) from orders where visible=true and order_type='CHECK_UP'", nativeQuery = true)
    Long countReviewCheckUp();

    @Query(value = "select * from orders where visible=false and  order_type='CHECK_UP'", nativeQuery = true)
    Page<CheckUp> findAllSuccessCheckUpVisibleAdmin(Pageable pageable);

    @Query(value = "select * from orders where visible=true and payment = true and order_type='CHECK_UP'", nativeQuery = true)
    Page<CheckUp> findAllCheckUpVisibleDoctor(Pageable pageable);

    @Query(value = "select * from orders where visible=false and  order_type='CHECK_UP'and patient_id =:idPatient", nativeQuery = true)
    Page<CheckUp> findAllSuccessCheckUpPatient(Pageable pageable, @Param("idPatient") Long idPatient);
}