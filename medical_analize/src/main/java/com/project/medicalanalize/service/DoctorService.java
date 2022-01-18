package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DoctorService extends BaseCrudService<Doctor>{

    Set<TranscriptOrder> findDoctorAllVisibleTranscript();

    Set<CheckUp> findDoctorAllVisibleCheckUp();

    Set<ConsultationOrder> findDoctorAllVisibleConsultation();
}