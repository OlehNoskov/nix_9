package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Transcript;

public class TranscriptResponseDto extends ResponseDto {

    private Integer price;
    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String burglaryComplaints;
    private String hereditary_diseases;
    private String featuresNutrition;
    private String file;
//    private Patient patient;
//    private Doctor doctor;


    public TranscriptResponseDto(Transcript transcript) {
        setId(transcript.getId());
        setCreated(transcript.getCreated());
        setUpdated(transcript.getUpdated());
        setVisible(transcript.getVisible());
        this.price = transcript.getPrice();
        this.badHabits = transcript.getBadHabits();
        this.drugsTaken = transcript.getDrugsTaken();
        this.chronicDiseases = transcript.getChronicDiseases();
        this.burglaryComplaints = transcript.getBurglaryComplaints();
        this.hereditary_diseases = transcript.getHereditary_diseases();
        this.featuresNutrition = transcript.getFeaturesNutrition();
        this.file = transcript.getFile();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits;
    }

    public String getDrugsTaken() {
        return drugsTaken;
    }

    public void setDrugsTaken(String drugsTaken) {
        this.drugsTaken = drugsTaken;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getBurglaryComplaints() {
        return burglaryComplaints;
    }

    public void setBurglaryComplaints(String burglaryComplaints) {
        this.burglaryComplaints = burglaryComplaints;
    }

    public String getHereditary_diseases() {
        return hereditary_diseases;
    }

    public void setHereditary_diseases(String hereditary_diseases) {
        this.hereditary_diseases = hereditary_diseases;
    }

    public String getFeaturesNutrition() {
        return featuresNutrition;
    }

    public void setFeaturesNutrition(String featuresNutrition) {
        this.featuresNutrition = featuresNutrition;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//
//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
}   