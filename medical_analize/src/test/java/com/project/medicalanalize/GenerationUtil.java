package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import java.util.Date;

public final class GenerationUtil {

    public GenerationUtil() {
    }

    public static Patient generatePatient(String firstName, String lastName, String email, String password) {
        Patient patient = new Patient();
        patient.setBirthDay(new Date());
        patient.setRoleType(RoleType.ROLE_PATIENT);
        patient.setEnabled(true);
        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        patient.setEmail(email);
        patient.setPassword(password);
        return patient;
    }

    public static Doctor generateDoctor(String firstName, String lastName, String email, String password) {
        Doctor doctor = new Doctor();
        doctor.setBirthDay(new Date());
        doctor.setRoleType(RoleType.ROLE_DOCTOR);
        doctor.setEnabled(true);
        doctor.setLastName(lastName);
        doctor.setFirstName(firstName);
        doctor.setEmail(email);
        doctor.setPassword(password);
        return doctor;
    }

    public static TranscriptOrder generateTranscriptOrder(){
        TranscriptOrder transcriptOrder = new TranscriptOrder();
        transcriptOrder.setCreated(new Date());
        transcriptOrder.setUpdated(new Date());
        transcriptOrder.setVisible(true);
        transcriptOrder.setPayment(true);
        transcriptOrder.setBadHabits("test");
        transcriptOrder.setChronicDiseases("no");
        transcriptOrder.setDrugsTaken("no");
        transcriptOrder.setFeaturesNutrition("no");
        transcriptOrder.setHereditary_diseases("no");
        transcriptOrder.setComplaints("Loss of energy, swelling, headaches. Constant fatigue, painful appearance, swelling.");
        return transcriptOrder;
    }

    public static CheckUp generateCheckUpOrder(){
        CheckUp checkUp = new CheckUp();
        checkUp.setCreated(new Date());
        checkUp.setUpdated(new Date());
        checkUp.setVisible(true);
        checkUp.setPayment(false);
        checkUp.setBadHabits("test");
        checkUp.setChronicDiseases("no");
        checkUp.setDrugsTaken("no");
        checkUp.setFeaturesNutrition("no");
        checkUp.setHereditary_diseases("no");
        checkUp.setComplaints("Loss of energy, swelling, headaches. Constant fatigue.");
        return checkUp;
    }

    public static ConsultationOrder generateConsultationOrder(){
        ConsultationOrder consultationOrder = new ConsultationOrder();
        consultationOrder.setCreated(new Date());
        consultationOrder.setUpdated(new Date());
        consultationOrder.setVisible(true);
        consultationOrder.setPayment(false);
        consultationOrder.setBadHabits("test");
        consultationOrder.setChronicDiseases("no");
        consultationOrder.setDrugsTaken("no");
        consultationOrder.setFeaturesNutrition("no");
        consultationOrder.setHereditary_diseases("no");
        consultationOrder.setComplaints("Loss of energy, swelling, headaches. Constant fatigue.");
        return consultationOrder;
    }

    public static Feedback generateFeedback(){
        Feedback feedback = new Feedback();
        feedback.setCreated(new Date());
        feedback.setUpdated(new Date());
        feedback.setVisible(true);
        feedback.setTextFeedback("test");
        return feedback;
    }
}