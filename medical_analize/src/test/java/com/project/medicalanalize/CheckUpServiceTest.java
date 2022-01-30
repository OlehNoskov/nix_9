package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.service.CheckUpService;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CheckUpServiceTest {

    @Autowired
    CheckUpService checkUpService;
    private final int ITEMS_SIZE = 0;
//    private final int sizeAllPatients = ITEMS_SIZE + 2;

    @Order(1)
    @Test
    void shouldBeCreateTranscriptWhenAllFieldsIsNotEmpty() {
        CheckUp checkUp = GenerationUtil.generateCheckUpOrder();
        checkUpService.create(checkUp);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    public void shouldBeUpdateDrugsTakenWhenFieldsIsNotNull() {
        CheckUp checkUp = checkUpService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        checkUp.setDrugsTaken("fish oil, omega 3");
        checkUpService.update(checkUp);
        Assertions.assertEquals("fish oil, omega 3", checkUp.getDrugsTaken());
    }

    @Order(3)
    @Test
    public void shouldBeUpdateCheckUpWhenOrderPayment() {
        CheckUp checkUp = checkUpService.findAll().stream().filter(drugs -> drugs.getDrugsTaken().equals("fish oil, omega 3")).toList().get(0);
        checkUp.setPayment(true);
        checkUpService.update(checkUp);
        Assertions.assertEquals(true, checkUp.getPayment());
    }

    @Order(4)
    @Test
    public void shouldBeUpdateCheckUpAnswerWhenOrderSuccess() {
        CheckUp checkUp = checkUpService.findAll().stream().filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        checkUp.setAnswer("update answer");
        checkUp.setVisible(false);
        checkUpService.update(checkUp);
        Assertions.assertEquals("update answer", checkUp.getAnswer());
        Assertions.assertEquals(false, checkUp.getVisible());
    }

    @Order(5)
    @Test
    public void shouldBeDeleteCheckUpOrderWhenOrderSuccess() {
        CheckUp checkUp = checkUpService.findAll().stream()
                .filter(badHabits -> badHabits.getBadHabits().equals("test")).toList().get(0);
        checkUpService.delete(checkUp.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<CheckUp> items = checkUpService.findAll();
        Assertions.assertEquals(size, items.size());
    }
}