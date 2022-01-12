package com.project.medicalanalize.persistence.repository.feedback;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.repository.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface FeedbacksRepository  <F extends Feedback> extends BaseRepository<F> {
}