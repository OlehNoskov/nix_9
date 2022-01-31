package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ConsultationServiceTest {

    @Autowired
    ComprehensiveConsultationOrderService consultationOrderService;
    private final int ITEMS_SIZE = 0;

    @Order(1)
    @Test
    void shouldBeCreateTranscriptWhenAllFieldsIsNotEmpty() {
        ConsultationOrder consultationOrder = GenerationUtil.generateConsultationOrder();
        consultationOrderService.create(consultationOrder);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeUpdateDrugsTakenWhenFieldsIsNotNull() {
        ConsultationOrder consultationOrder = consultationOrderService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        consultationOrder.setDrugsTaken("fish oil, omega 3");
        consultationOrderService.update(consultationOrder);
        Assertions.assertEquals("fish oil, omega 3", consultationOrder.getDrugsTaken());
    }

    @Order(3)
    @Test
    public void shouldBeUpdateConsultationWhenOrderPayment() {
        ConsultationOrder consultationOrder = consultationOrderService.findAll().stream().filter(drugs -> drugs.getDrugsTaken().equals("fish oil, omega 3")).toList().get(0);
        consultationOrder.setPayment(true);
        consultationOrderService.update(consultationOrder);
        Assertions.assertEquals(true, consultationOrder.getPayment());
    }

    @Order(4)
    @Test
    public void shouldBeUpdateConsultationAnswerWhenOrderSuccess() {
        ConsultationOrder consultationOrder = consultationOrderService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        consultationOrder.setAnswer("update answer");
        consultationOrder.setVisible(false);
        consultationOrderService.update(consultationOrder);
        Assertions.assertEquals("update answer", consultationOrder.getAnswer());
        Assertions.assertEquals(false, consultationOrder.getVisible());
    }

    @Order(5)
    @Test
    public void shouldBeDeleteConsultationOrderWhenOrderSuccess() {
        ConsultationOrder consultationOrder = consultationOrderService.findAll().stream()
                .filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        consultationOrderService.delete(consultationOrder.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<ConsultationOrder> items = consultationOrderService.findAll();
        Assertions.assertEquals(size, items.size());
    }
}