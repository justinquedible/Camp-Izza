package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.MedicalRecord;

import java.util.Date;

public class CamperInfoResponse {
    private String firstName;

    private String lastName;

    private String genderEnum;

    private String dobDate;

    private String schoolName;

    private Integer gradeNum;

    private String shirtEnum;

    private String doctorName;

    private String doctorPhone;

    private String insurance;

    private String policy_holder;

    private String illnesses;

    private String allergies;

    private String medication;

    private String medication_names;

    private String activities;

    private String activity_names;

    private String medical_treatments;

    private String medical_treatment_names;

    private String immunizations;

    private String tetanus_date;

    private String comment;

    private Long camperID;

    private String illnesses_names;

    private String allergy_names;

    // @TODO: missing medical record



    private Long userID;

    public CamperInfoResponse(Camper camperRequest, MedicalRecord medicalrecordRequest) {
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dobDate = camperRequest.getDobDate();
        this.gradeNum = camperRequest.getGradeNum();
        this.schoolName = camperRequest.getSchoolName();
        this.comment = camperRequest.getComment();
        this.genderEnum = camperRequest.getGenderEnum();
        this.shirtEnum = camperRequest.getShirtEnum();
        this.doctorName = medicalrecordRequest.getDoctorName();
        this.doctorPhone = medicalrecordRequest.getDoctorPhone();
        this.insurance = medicalrecordRequest.getInsurance();
        this.policy_holder = medicalrecordRequest.getPolicyHolder();
        this.illnesses = medicalrecordRequest.getIllnesses();
        this.allergies = medicalrecordRequest.getAllergies();
        this.medication = medicalrecordRequest.getMedication();
        this.medication_names = medicalrecordRequest.getMedicationNames();
        this.activities = medicalrecordRequest.getActivities();
        this.activity_names = medicalrecordRequest.getActivitiesName();
        this.medical_treatments = medicalrecordRequest.getMedicalTreatments();
        this.medical_treatment_names = medicalrecordRequest.getMedicalTreatmentsNames();
        this.immunizations = medicalrecordRequest.getImmunizations();
        this.tetanus_date = medicalrecordRequest.getTetanusDate();
        this.allergy_names = medicalrecordRequest.getAllergy_names();
        this.illnesses_names = medicalrecordRequest.getIllnesses_names();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public Long getCamperID() {
        return camperID;
    }

    public void setCamperID(Long camperID) {
        this.camperID = camperID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getGenderEnum() {
        return genderEnum;
    }

    public void setGenderEnum(String genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShirtEnum() {
        return shirtEnum;
    }

    public void setShirtEnum(String shirtEnum) {
        this.shirtEnum = shirtEnum;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPolicy_holder() {
        return policy_holder;
    }

    public void setPolicy_holder(String policy_holder) {
        this.policy_holder = policy_holder;
    }

    public String getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(String illnesses) {
        this.illnesses = illnesses;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getMedication_names() {
        return medication_names;
    }

    public void setMedication_names(String medication_names) {
        this.medication_names = medication_names;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getActivity_names() {
        return activity_names;
    }

    public void setActivity_names(String activity_names) {
        this.activity_names = activity_names;
    }

    public String getMedical_treatments() {
        return medical_treatments;
    }

    public void setMedical_treatments(String medical_treatments) {
        this.medical_treatments = medical_treatments;
    }

    public String getMedical_treatment_names() {
        return medical_treatment_names;
    }

    public void setMedical_treatment_names(String medical_treatment_names) {
        this.medical_treatment_names = medical_treatment_names;
    }

    public String getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(String immunizations) {
        this.immunizations = immunizations;
    }

    public String getTetanus_date() {
        return tetanus_date;
    }

    public void setTetanus_date(String tetanus_date) {
        this.tetanus_date = tetanus_date;
    }

    public String getIllnesses_names() {
        return illnesses_names;
    }

    public void setIllnesses_names(String illnesses_names) {
        this.illnesses_names = illnesses_names;
    }

    public String getAllergy_names() {
        return allergy_names;
    }

    public void setAllergy_names(String allergy_names) {
        this.allergy_names = allergy_names;
    }
}