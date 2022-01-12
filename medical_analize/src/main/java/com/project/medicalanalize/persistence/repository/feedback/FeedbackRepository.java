package com.project.medicalanalize.persistence.repository.feedback;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;

import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends FeedbacksRepository<Feedback> {
}