package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.service.FeedbackService;
import com.project.medicalanalize.service.PatientService;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class FeedbackServiceTest {

    @Autowired
    FeedbackService feedbackService;
    private final int ITEMS_SIZE = 0;
    //    private final int sizeAllPatients = ITEMS_SIZE + 2;

    @Autowired
    PatientService patientService;

    @Order(1)
    @Test
    void shouldBeCreateFeedbackWhenAllFieldsIsNotEmpty() {
        Feedback feedback = GenerationUtil.generateFeedback();
        feedback.setPatient(patientService.findAll().stream().findFirst().get());
        feedbackService.create(feedback);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeUpdateTextFeedbackWhenFieldsIsNotNull() {
        Feedback feedback = feedbackService.findAll().stream().filter(text -> text.getTextFeedback().equals("test")).toList().get(0);
        feedback.setTextFeedback("update text feedback");
        feedbackService.update(feedback);
        Assertions.assertEquals("update text feedback", feedback.getTextFeedback());
    }

    @Order(3)
    @Test
    public void shouldBeDeleteFeedbackWhenIsValidFeedback() {
        Feedback feedback = feedbackService.findAll().stream()
                .filter(text -> text.getTextFeedback().equals("update text feedback")).toList().get(0);
        feedbackService.delete(feedback.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Feedback> items = feedbackService.findAll();
        Assertions.assertEquals(size, items.size());
    }
}