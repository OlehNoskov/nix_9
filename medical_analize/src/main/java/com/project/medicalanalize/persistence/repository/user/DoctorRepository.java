package com.project.medicalanalize.persistence.repository.user;

import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {

    @Query(value = "select * from orders where visible=true and order_type='TRANSCRIPT'")
    Set<TranscriptOrder> findDoctorAllVisibleTranscript();

    @Query(value = "select * from orders where visible=true and order_type='CHECK_UP'")
    Set<CheckUp> findDoctorAllVisibleCheckUp();

    @Query(value = "select * from orders where visible=true and order_type='COMPREHENSIVE_CONSULTATION'")
    Set<ConsultationOrder> findDoctorAllVisibleConsultation();
}