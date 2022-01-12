package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.type.OrderType;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TRANSCRIPT")
public class Transcript extends Order {

    @Column(name = "price")
    private Integer price;

    @Column(name = "bad_habits")
    private String badHabits;

    @Column(name = "drugs_taken")
    private String drugsTaken;

    @Column(name = "chronic_diseases")
    private String chronicDiseases;

    @Column(name = "burglary_complaints")
    private String burglaryComplaints;

    @Column(name = "hereditary_diseases")
    private String hereditary_diseases;

    @Column(name = "features_nutrition")
    private String featuresNutrition;

    @Column(name = "link_file")
    private String file;

    public Transcript() {
        super();
        setOrderType(OrderType.TRANSCRIPT);
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

    public Integer getPrice() {
        return 6;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}