package com.project.medicalanalize.persistence.repository.feedback;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends FeedbacksRepository<Feedback> {

    @Query(value = "select * from feedbacks where patient_id =:idPatient", nativeQuery = true)
    Page<Feedback> findAllFeedbacksPatient(Pageable pageable, @Param("idPatient") Long idPatient);
}