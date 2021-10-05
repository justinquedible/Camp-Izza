package com.campizza.backend.campizzabackend.model;

import com.campizza.backend.campizzabackend.security.payloads.CamperRequest;
import com.campizza.backend.campizzabackend.security.payloads.MedicalRecordRequest;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_phone")
    private String doctorPhone;

    @Column(name = "insurance")
    private String insurance;

    @Column(name = "policy_holder")
    private String policyHolder;

    @Column(name = "illnesses")
    private String illnesses;

    @Column(name = "illnesses_names")
    private String illnesses_names;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "allergy_names")
    private String allergy_names;

    @Column(name = "medication")
    private String medication;

    @Column(name = "medication_names")
    private String medicationNames;

    @Column(name="activities")
    private String activities;

    @Column(name = "activities_name")
    private String activitiesName;

    @Column(name = "medical_treatments")
    private String medicalTreatments;

    @Column(name = "medical_treatments_names")
    private String medicalTreatmentsNames;

    @Column(name = "immunizations")
    private String immunizations;

    @Column(name = "tetanus_date")
    private String tetanusDate;

    @Column(name = "comments")
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "camper_id")
    private Camper  camper;

    public MedicalRecord(){

    }

    public MedicalRecord(MedicalRecordRequest medicalRecordRequest){
        this.firstName = medicalRecordRequest.getFirstName();
        this.lastName = medicalRecordRequest.getLastName();
        //this.dob = medicalRecordRequest.getDob();
        this.doctorName = medicalRecordRequest.getDoctorName();
        this.doctorPhone = medicalRecordRequest.getDoctorPhone();
        this.insurance = medicalRecordRequest.getInsurance();
        this.policyHolder = medicalRecordRequest.getPolicy_holder();
        this.illnesses = medicalRecordRequest.getIllnesses();
        this.allergies = medicalRecordRequest.getAllergies();
        this.medication = medicalRecordRequest.getMedication();
        this.medicationNames = medicalRecordRequest.getMedication_names();
        this.activities = medicalRecordRequest.getActivities();
        this.activitiesName = medicalRecordRequest.getActivity_names();
        this.medicalTreatments = medicalRecordRequest.getMedical_treatments();
        this.medicalTreatmentsNames = medicalRecordRequest.getMedical_treatment_names();
        this.immunizations = medicalRecordRequest.getImmunizations();
        this.tetanusDate = medicalRecordRequest.getTetanus_date();
        this.camper = camper;
    }

    public MedicalRecord(String firstName, String lastName, String dob, String doctorName, String doctorPhone, String insurance, String policyHolder,
                         String illnesses, String illnesses_names, String allergies, String allergy_names, String medication, String medicationNames,
                         String activities, String activitiesName, String medicalTreatments, String medicalTreatmentsNames, String immunizations, String tetanusDate, String comment, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.insurance = insurance;
        this.policyHolder = policyHolder;
        this.illnesses = illnesses;
        this.illnesses_names = illnesses_names;
        this.allergies = allergies;
        this.allergy_names = allergy_names;
        this.medication = medication;
        this.medicationNames = medicationNames;
        this.activities = activities;
        this.activitiesName = activitiesName;
        this.medicalTreatments = medicalTreatments;
        this.medicalTreatmentsNames = medicalTreatmentsNames;
        this.immunizations = immunizations;
        this.tetanusDate = tetanusDate;
        this.user = user;
    }

    public void updateInfo(CamperRequest camperRequest, User user, Camper camper){
        this.firstName = camperRequest.getFirstName();
        this.lastName = camperRequest.getLastName();
        this.dob = camperRequest.getDobDate();
        this.doctorName = camperRequest.getDoctorName();
        this.doctorPhone = camperRequest.getDoctorPhone();
        this.insurance = camperRequest.getInsurance();
        this.policyHolder = camperRequest.getPolicy_holder();
        this.illnesses = camperRequest.getIllnesses();
        this.illnesses_names = camperRequest.getIllnesses_names();
        this.allergy_names = camperRequest.getAllergy_names();
        this.allergies = camperRequest.getAllergies();
        this.medication = camperRequest.getMedication();
        this.medicationNames = camperRequest.getMedication_names();
        this.activities = camperRequest.getActivities();
        this.activitiesName = camperRequest.getActivity_names();
        this.medicalTreatments = camperRequest.getMedical_treatments();
        this.medicalTreatmentsNames = camperRequest.getMedical_treatment_names();
        this.immunizations = camperRequest.getImmunizations();
        this.tetanusDate = camperRequest.getTetanus_date();
        this.comment = camperRequest.getComment();
        this.user = user;
        this.camper = camper;
    }

    public void updateCamper(Camper camper){
        this.camper = camper;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
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

    public String getMedicationNames() {
        return medicationNames;
    }

    public void setMedicationNames(String medicationNames) {
        this.medicationNames = medicationNames;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getActivitiesName() {
        return activitiesName;
    }

    public void setActivitiesName(String activitiesName) {
        this.activitiesName = activitiesName;
    }

    public String getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(String medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }

    public String getMedicalTreatmentsNames() {
        return medicalTreatmentsNames;
    }

    public void setMedicalTreatmentsNames(String medicalTreatmentsNames) {
        this.medicalTreatmentsNames = medicalTreatmentsNames;
    }

    public String getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(String immunizations) {
        this.immunizations = immunizations;
    }

    public String getTetanusDate() {
        return tetanusDate;
    }

    public void setTetanusDate(String tetanusDate) {
        this.tetanusDate = tetanusDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //    public Camper getCamper() {
//        return camper;
//    }
//
//    public void setCamper(Camper camper) {
//        this.camper = camper;
//    }
}