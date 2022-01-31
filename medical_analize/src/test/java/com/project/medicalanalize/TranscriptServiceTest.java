package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.service.TranscriptService;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TranscriptServiceTest {

    @Autowired
    TranscriptService transcriptService;
    private final int ITEMS_SIZE = 0;

    @Order(1)
    @Test
    void shouldBeCreateTranscriptWhenAllFieldsIsNotEmpty() {
        TranscriptOrder transcriptOrder = GenerationUtil.generateTranscriptOrder();
        transcriptService.create(transcriptOrder);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeUpdateDrugsTakenWhenFieldsIsNotNull() {
        TranscriptOrder transcriptOrder = transcriptService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        transcriptOrder.setDrugsTaken("fish oil, omega 3");
        transcriptService.update(transcriptOrder);
        Assertions.assertEquals("fish oil, omega 3", transcriptOrder.getDrugsTaken());
    }

    @Order(3)
    @Test
    public void shouldBeUpdateTranscriptWhenOrderPayment() {
        TranscriptOrder transcriptOrder = transcriptService.findAll().stream().filter(drugs -> drugs.getDrugsTaken().equals("fish oil, omega 3")).toList().get(0);
        transcriptOrder.setPayment(true);
        transcriptService.update(transcriptOrder);
        Assertions.assertEquals(true, transcriptOrder.getPayment());
    }

    @Order(4)
    @Test
    public void shouldBeUpdateTranscriptAnswerWhenOrderSuccess() {
        TranscriptOrder transcriptOrder = transcriptService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        transcriptOrder.setAnswer("update answer");
        transcriptOrder.setVisible(false);
        transcriptService.update(transcriptOrder);
        Assertions.assertEquals("update answer", transcriptOrder.getAnswer());
        Assertions.assertEquals(false, transcriptOrder.getVisible());
    }

    @Order(5)
    @Test
    public void shouldBeDeleteTranscriptOrderWhenOrderSuccess() {
        TranscriptOrder transcriptOrder = transcriptService.findAll().stream()
                .filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        transcriptService.delete(transcriptOrder.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<TranscriptOrder> items = transcriptService.findAll();
        Assertions.assertEquals(size, items.size());
    }
}