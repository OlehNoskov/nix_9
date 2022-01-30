package com.project.medicalanalize;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.service.DoctorService;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class DoctorServiceTest {

    @Autowired
    DoctorService doctorService;
    private final int ITEMS_SIZE = 1;
    private final int sizeAllDoctors = ITEMS_SIZE + 1;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Doctor doctor = GenerationUtil.generateDoctor("first name", "last name", "doctorTest@gmail.com", "rootroot");
            doctorService.create(doctor);
        }
        Assertions.assertEquals(sizeAllDoctors, doctorService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeCreateDoctorWhenAllFieldsIsNotEmpty() {
        Doctor doctor = GenerationUtil.generateDoctor("first name", "last name", "doctorTest1@gmail.com", "rootroot");
        doctorService.create(doctor);
        verifyItemsWhenItemsIsNotEmpty(sizeAllDoctors + 1);
    }

    @Order(2)
    @Test
    public void shouldBeCreateDoctorWhenPatientNameIsEmpty() {
        Doctor doctor = new Doctor();
        doctor.setEmail("doctorTestNameNull@gmail.com");
        doctor.setPassword("rootroot");
        doctor.setFirstName(null);
        doctor.setLastName("Noskov");
        doctorService.create(doctor);
        verifyItemsWhenItemsIsNotEmpty(sizeAllDoctors + 2);
    }

    @Order(3)
    @Test
    public void shouldBeCreateDoctorWhenDoctorLastNameIsEmpty() {
        Doctor doctor = new Doctor();
        doctor.setEmail("doctorTestLastNameNull@gmail.com");
        doctor.setPassword("rootroot");
        doctor.setFirstName("Irina");
        doctor.setLastName(null);
        doctorService.create(doctor);
        verifyItemsWhenItemsIsNotEmpty(sizeAllDoctors + 3);
    }

    @Order(3)
    @Test
    public void shouldBeUpdateNamePatientWhenNameIsNotNull() {
        Doctor doctor = doctorService.findAll().stream().filter(doctor1 -> doctor1.getEmail().equals("doctorTestLastNameNull@gmail.com")).toList().get(0);
        doctor.setFirstName("Oleg");
        doctorService.update(doctor);
        Assertions.assertEquals("Oleg", doctor.getFirstName());
    }

    @Order(4)
    @Test
    public void shouldBeUpdateLastNamePatientWhenLatNameIsNotNull() {
        Doctor doctor = doctorService.findAll().stream().filter(doctor1 -> doctor1.getEmail().equals("doctorTestNameNull@gmail.com")).toList().get(0);
        doctor.setLastName("Prihodko");
        doctorService.update(doctor);
        Assertions.assertEquals("Prihodko", doctor.getLastName());
    }

    @Order(5)
    @Test
    void shouldBeReturnExceptionWhenCreatedPatientWhenEmailIsAlreadyExist() {
        Exception exception = assertThrows(EntityExistException.class, () -> {
            Doctor doctor = GenerationUtil.generateDoctor("first name", "last name", "doctorTest1@gmail.com", "rootroot");
            doctorService.create(doctor);
        });
        verifyItemsWhenItemsIsNotEmpty(sizeAllDoctors + 3);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeletePatient() {
        Doctor doctor = doctorService.findAll().stream().filter(doctor1 -> doctor1.getEmail().equals("doctorTest1@gmail.com")).toList().get(0);
        doctorService.delete(doctor.getId());
        verifyItemsWhenItemsIsNotEmpty(sizeAllDoctors + 2);
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Doctor> items = doctorService.findAll();
        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}