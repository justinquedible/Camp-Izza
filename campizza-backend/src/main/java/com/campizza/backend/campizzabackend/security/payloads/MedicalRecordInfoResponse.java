package com.campizza.backend.campizzabackend.security.payloads;
import com.campizza.backend.campizzabackend.model.MedicalRecord;

public class MedicalRecordInfoResponse {
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


    MedicalRecordInfoResponse(MedicalRecord medicalRecord){
        this.doctorName = medicalRecord.getDoctorName();
        this.doctorPhone = medicalRecord.getDoctorPhone();
        this.insurance = medicalRecord.getInsurance();
        this.policy_holder = medicalRecord.getPolicyHolder();
        this.illnesses = medicalRecord.getIllnesses();
        this.medication = medicalRecord.getMedication();
        this.medication_names = medicalRecord.getMedicationNames();
        this.activities = medicalRecord.getActivities();
        this.activity_names = medicalRecord.getActivitiesName();
        this.medical_treatments = medicalRecord.getMedicalTreatments();
        this.medical_treatment_names = medicalRecord.getMedicalTreatmentsNames();
        this.immunizations = medicalRecord.getImmunizations();
        this.tetanus_date = medicalRecord.getTetanusDate();

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
}
