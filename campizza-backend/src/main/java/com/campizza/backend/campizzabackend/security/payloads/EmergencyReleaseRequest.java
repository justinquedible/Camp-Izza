package com.campizza.backend.campizzabackend.security.payloads;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmergencyReleaseRequest {
    private Long camperId;
    private boolean personalfirstName;
    private boolean personallastName;
    private boolean personalgender;
    private boolean personaldob;
    private boolean personalschool;
    private boolean personalgrade;
    private boolean weeksextendedCare;
    private boolean medicalInfodoctorName;
    private boolean medicalInfodoctorPhone;
    private boolean medicalInfoinsurance;
    private boolean medicalInfopolicyholder;
    private boolean medicalInfoillnesses;
    private boolean medicalInfoallergiesDiet;
    private boolean medicalInfomedications;
    private boolean medicalInfoactivities;
    private boolean medicalInfoactivityNames;
    private boolean medicalInfotreatments;
    private boolean medicalInfotreatmentNames;
    private boolean medicalInfoimmunizations;
    private boolean medicalInfotetanus;
    private boolean medicalInfocomments;
    private boolean parentg1FirstName;
    private boolean parentg1LastName;
    private boolean parentg2FirstName;
    private boolean parentg2LastName;
    private boolean parentemail1;
    private boolean parentemail2;
    private boolean parentaddress1;
    private boolean parentg1Phone1;
    private boolean parentg1Phone2;
    private boolean parentg2Phone1;
    private boolean parentg2Phone2;
    private boolean emergencye1FirstName;
    private boolean emergencye1LastName;
    private boolean emergencye1Relationship;
    private boolean emergencye1Phone;
    private boolean emergencye2FirstName;
    private boolean emergencye2LastName;
    private boolean emergencye2Relationship;
    private boolean emergencye2Phone;

    public List<String> getAttributes() throws IllegalAccessException {
        ArrayList<String> result = new ArrayList<>();
        for (Field f : this.getClass().getDeclaredFields()) {
            if (f.get(this).toString().equals("true")) {
                result.add(f.getName());
            }
        }
        return result;
    }

    public Long getCamperId() {
        return camperId;
    }

    public boolean isParentaddress1() {
        return parentaddress1;
    }

    public boolean isPersonalfirstName() {
        return personalfirstName;
    }

    public boolean isPersonallastName() {
        return personallastName;
    }

    public boolean isPersonalgender() {
        return personalgender;
    }

    public boolean isPersonaldob() {
        return personaldob;
    }

    public boolean isPersonalschool() {
        return personalschool;
    }

    public boolean isPersonalgrade() {
        return personalgrade;
    }

    public boolean isWeeksextendedCare() {
        return weeksextendedCare;
    }

    public boolean isMedicalInfodoctorName() {
        return medicalInfodoctorName;
    }

    public boolean isMedicalInfodoctorPhone() {
        return medicalInfodoctorPhone;
    }

    public boolean isMedicalInfoinsurance() {
        return medicalInfoinsurance;
    }

    public boolean isMedicalInfopolicyholder() {
        return medicalInfopolicyholder;
    }

    public boolean isMedicalInfoillnesses() {
        return medicalInfoillnesses;
    }

    public boolean isMedicalInfoallergiesDiet() {
        return medicalInfoallergiesDiet;
    }

    public boolean isMedicalInfomedications() {
        return medicalInfomedications;
    }

    public boolean isMedicalInfoactivities() {
        return medicalInfoactivities;
    }

    public boolean isMedicalInfoactivityNames() {
        return medicalInfoactivityNames;
    }

    public boolean isMedicalInfotreatments() {
        return medicalInfotreatments;
    }

    public boolean isMedicalInfotreatmentNames() {
        return medicalInfotreatmentNames;
    }

    public boolean isMedicalInfoimmunizations() {
        return medicalInfoimmunizations;
    }

    public boolean isMedicalInfotetanus() {
        return medicalInfotetanus;
    }

    public boolean isMedicalInfocomments() {
        return medicalInfocomments;
    }

    public boolean isParentg1FirstName() {
        return parentg1FirstName;
    }

    public boolean isParentg1LastName() {
        return parentg1LastName;
    }

    public boolean isParentg2FirstName() {
        return parentg2FirstName;
    }

    public boolean isParentg2LastName() {
        return parentg2LastName;
    }

    public boolean isParentemail1() {
        return parentemail1;
    }

    public boolean isParentemail2() {
        return parentemail2;
    }

    public boolean isParentg1Phone1() {
        return parentg1Phone1;
    }

    public boolean isParentg1Phone2() {
        return parentg1Phone2;
    }

    public boolean isParentg2Phone1() {
        return parentg2Phone1;
    }

    public boolean isParentg2Phone2() {
        return parentg2Phone2;
    }

    public boolean isEmergencye1FirstName() {
        return emergencye1FirstName;
    }

    public boolean isEmergencye1LastName() {
        return emergencye1LastName;
    }

    public boolean isEmergencye1Relationship() {
        return emergencye1Relationship;
    }

    public boolean isEmergencye1Phone() {
        return emergencye1Phone;
    }

    public boolean isEmergencye2FirstName() {
        return emergencye2FirstName;
    }

    public boolean isEmergencye2LastName() {
        return emergencye2LastName;
    }

    public boolean isEmergencye2Relationship() {
        return emergencye2Relationship;
    }

    public boolean isEmergencye2Phone() {
        return emergencye2Phone;
    }
}
