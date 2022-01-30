package com.project.medicalanalize;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.PatientService;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    PatientService patientService;
    private final int ITEMS_SIZE = 1;
    private final int sizeAllPatients = ITEMS_SIZE + 1;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Patient patient = GenerationUtil.generatePatient("first name" + i, "last name" + i, "patientTest" + i + "@gmail.com", "rootroot");
            patientService.create(patient);
        }
        Assertions.assertEquals(sizeAllPatients, patientService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeCreatePatientWhenAllFieldsIsNotEmpty() {
        Patient patient = GenerationUtil.generatePatient("first name", "last name", "patientTest@gmail.com", "rootroot");
        patientService.create(patient);
        verifyItemsWhenItemsIsNotEmpty(sizeAllPatients + 1);
    }

    @Order(2)
    @Test
    public void shouldBeCreatePatientWhenPatientNameIsEmpty() {
        Patient patient = new Patient();
        patient.setEmail("patientTestNameNull@gmail.com");
        patient.setPassword("rootroot");
        patient.setFirstName(null);
        patient.setLastName("Noskov");
        patientService.create(patient);
        verifyItemsWhenItemsIsNotEmpty(sizeAllPatients + 2);
    }

    @Order(3)
    @Test
    public void shouldBeCreatePatientWhenPatientLastNameIsEmpty() {
        Patient patient = new Patient();
        patient.setEmail("patientTestLastNameNull@gmail.com");
        patient.setPassword("rootroot");
        patient.setFirstName("Oleg");
        patient.setLastName(null);
        patientService.create(patient);
        verifyItemsWhenItemsIsNotEmpty(sizeAllPatients + 3);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateNamePatientWhenNameIsNotNull() {
        Patient patient = patientService.findAll().stream().filter(patient1 -> patient1.getEmail().equals("patientTestNameNull@gmail.com")).toList().get(0);
        patient.setFirstName("Sergey");
        patientService.update(patient);
        Assertions.assertEquals("Sergey", patient.getFirstName());
    }

    @Order(4)
    @Test
    public void shouldBeUpdateLastNamePatientWhenLatNameIsNotNull() {
        Patient patient = patientService.findAll().stream().filter(patient1 -> patient1.getEmail().equals("patientTestNameNull@gmail.com")).toList().get(0);
        patient.setLastName("Kysil");
        patientService.update(patient);
        Assertions.assertEquals("Kysil", patient.getLastName());
    }

    @Order(5)
    @Test
    void shouldBeReturnExceptionWhenCreatedPatientWhenEmailIsAlreadyExist() {
        Exception exception = assertThrows(EntityExistException.class, () -> {
            Patient patient = GenerationUtil.generatePatient("first name", "last name", "patientTest@gmail.com", "rootroot");
            patientService.create(patient);
        });
        verifyItemsWhenItemsIsNotEmpty(sizeAllPatients + 3);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeletePatient() {
        Patient patient = patientService.findAll().stream().filter(patient1 -> patient1.getEmail().equals("patientTest@gmail.com")).toList().get(0);
        patientService.delete(patient.getId());
        verifyItemsWhenItemsIsNotEmpty(sizeAllPatients + 2);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Patient> items = patientService.findAll();
        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}