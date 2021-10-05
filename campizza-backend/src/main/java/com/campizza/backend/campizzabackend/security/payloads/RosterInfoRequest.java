package com.campizza.backend.campizzabackend.security.payloads;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RosterInfoRequest {
    private int year;
    private boolean personalchildID;
    private boolean personalfirstName;
    private boolean personallastName;
    private boolean personalgender;
    private boolean personaldob;
    private boolean personalschool;
    private boolean personalgrade;
    private boolean personalshirtSize;
    private boolean personalnumShirts;
    private boolean personalallReg;
    private boolean personalaccCreated;
    private boolean personalgroup;
    private boolean weekswk1AM;
    private boolean weekswk1PM;
    private boolean weekswk2AM;
    private boolean weekswk2PM;
    private boolean weekswk3AM;
    private boolean weekswk3PM;
    private boolean weekswk4AM;
    private boolean weekswk4PM;
    private boolean weekswk5AM;
    private boolean weekswk5PM;
    private boolean weekswk6AM;
    private boolean weekswk6PM;
    private boolean weekswk7AM;
    private boolean weekswk7PM;
    private boolean weekswk8AM;
    private boolean weekswk8PM;
    private boolean weekswk9AM;
    private boolean weekswk9PM;
    private boolean weekswk10AM;
    private boolean weekswk10PM;
    private boolean weeksAll;
    private boolean weeksextendedCare;
    private boolean attendancewk1Expected;
    private boolean attendancewk1Actual;
    private boolean attendancewk2Expected;
    private boolean attendancewk2Actual;
    private boolean attendancewk3Expected;
    private boolean attendancewk3Actual;
    private boolean attendancewk4Expected;
    private boolean attendancewk4Actual;
    private boolean attendancewk5Expected;
    private boolean attendancewk5Actual;
    private boolean attendancewk6Expected;
    private boolean attendancewk6Actual;
    private boolean attendancewk7Expected;
    private boolean attendancewk7Actual;
    private boolean attendancewk8Expected;
    private boolean attendancewk8Actual;
    private boolean attendancewk9Expected;
    private boolean attendancewk9Actual;
    private boolean attendancewk10Expected;
    private boolean attendancewk10Actual;
    private boolean medicalInfodoctorName;
    private boolean medicalInfodoctorPhone;
    private boolean medicalInfoinsurance;
    private boolean medicalInfopolicyholder;
    private boolean medicalInfoillnesses;
    private boolean medicalInfoallergiesDiet;
    private boolean medicalInfomedications;
    private boolean medicalInfoactivities;
    private boolean medicalInfotreatments;
    private boolean medicalInfoimmunizations;
    private boolean medicalInfotetanus;
    private boolean medicalInfocomments;
    private boolean parentparentID;
    // private boolean parentregTime;
    // private boolean parentlocation;
    private boolean parentg1FirstName;
    private boolean parentg1LastName;
    private boolean parentg2FirstName;
    private boolean parentg2LastName;
    private boolean parentaddress1;
    private boolean parentaddress2;
    private boolean parentcountry;
    private boolean parentcity;
    private boolean parentstate;
    private boolean parentzipcode;
    private boolean parentemail1;
    private boolean parentemail2;
    private boolean parentg1Phone1;
    private boolean parentg1Phone2;
    private boolean parentg2Phone1;
    private boolean parentg2Phone2;
    private boolean emergency1FirstName;
    private boolean emergency1LastName;
    private boolean emergency1Relationship;
    private boolean emergency1Phone;
    private boolean emergency1Authorized;
    private boolean emergency2FirstName;
    private boolean emergency2LastName;
    private boolean emergency2Relationship;
    private boolean emergency2Phone;
    private boolean emergency2Authorized;
    private boolean paymentsamountPaid;
    private boolean paymentscredit;
    private boolean paymentsearlyBird;
    private boolean counselorscounselorID;
    private boolean counselorscounselorEmail;
    private boolean counselorscounselorGroup;
    private boolean counselorscounselorStatus;

    public RosterInfoRequest() {
    }

    public boolean isPersonalchildID() {
        return personalchildID;
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

    public boolean isPersonalshirtSize() {
        return personalshirtSize;
    }

    public boolean isPersonalnumShirts() {
        return personalnumShirts;
    }

    public boolean isPersonalallReg() {
        return personalallReg;
    }

    public boolean isPersonalaccCreated() {
        return personalaccCreated;
    }

    public boolean isPersonalgroup() {
        return personalgroup;
    }

    public boolean isWeekswk1AM() {
        return weekswk1AM;
    }

    public boolean isWeekswk1PM() {
        return weekswk1PM;
    }

    public boolean isWeekswk2AM() {
        return weekswk2AM;
    }

    public boolean isWeekswk2PM() {
        return weekswk2PM;
    }

    public boolean isWeekswk3AM() {
        return weekswk3AM;
    }

    public boolean isWeekswk3PM() {
        return weekswk3PM;
    }

    public boolean isWeekswk4AM() {
        return weekswk4AM;
    }

    public boolean isWeekswk4PM() {
        return weekswk4PM;
    }

    public boolean isWeekswk5AM() {
        return weekswk5AM;
    }

    public boolean isWeekswk5PM() {
        return weekswk5PM;
    }

    public boolean isWeekswk6AM() {
        return weekswk6AM;
    }

    public boolean isWeekswk6PM() {
        return weekswk6PM;
    }

    public boolean isWeekswk7AM() {
        return weekswk7AM;
    }

    public boolean isWeekswk7PM() {
        return weekswk7PM;
    }

    public boolean isWeekswk8AM() {
        return weekswk8AM;
    }

    public boolean isWeekswk8PM() {
        return weekswk8PM;
    }

    public boolean isWeekswk9AM() {
        return weekswk9AM;
    }

    public boolean isWeekswk9PM() {
        return weekswk9PM;
    }

    public boolean isWeekswk10AM() {
        return weekswk10AM;
    }

    public boolean isWeekswk10PM() {
        return weekswk10PM;
    }

    public boolean isWeeksAll() {
        return weeksAll;
    }

    public boolean isWeeksextendedCare() {
        return weeksextendedCare;
    }

    public boolean isAttendancewk1Expected() {
        return attendancewk1Expected;
    }

    public boolean isAttendancewk1Actual() {
        return attendancewk1Actual;
    }

    public boolean isAttendancewk2Expected() {
        return attendancewk2Expected;
    }

    public boolean isAttendancewk2Actual() {
        return attendancewk2Actual;
    }

    public boolean isAttendancewk3Expected() {
        return attendancewk3Expected;
    }

    public boolean isAttendancewk3Actual() {
        return attendancewk3Actual;
    }

    public boolean isAttendancewk4Expected() {
        return attendancewk4Expected;
    }

    public boolean isAttendancewk4Actual() {
        return attendancewk4Actual;
    }

    public boolean isAttendancewk5Expected() {
        return attendancewk5Expected;
    }

    public boolean isAttendancewk5Actual() {
        return attendancewk5Actual;
    }

    public boolean isAttendancewk6Expected() {
        return attendancewk6Expected;
    }

    public boolean isAttendancewk6Actual() {
        return attendancewk6Actual;
    }

    public boolean isAttendancewk7Expected() {
        return attendancewk7Expected;
    }

    public boolean isAttendancewk7Actual() {
        return attendancewk7Actual;
    }

    public boolean isAttendancewk8Expected() {
        return attendancewk8Expected;
    }

    public boolean isAttendancewk8Actual() {
        return attendancewk8Actual;
    }

    public boolean isAttendancewk9Expected() {
        return attendancewk9Expected;
    }

    public boolean isAttendancewk9Actual() {
        return attendancewk9Actual;
    }

    public boolean isAttendancewk10Expected() {
        return attendancewk10Expected;
    }

    public boolean isAttendancewk10Actual() {
        return attendancewk10Actual;
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

    public boolean isMedicalInfotreatments() {
        return medicalInfotreatments;
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

    public boolean isParentparentID() {
        return parentparentID;
    }

//    public boolean isParentregTime() {
//        return parentregTime;
//    }
//
//    public boolean isParentlocation() {
//        return parentlocation;
//    }

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

    public boolean isParentaddress1() {
        return parentaddress1;
    }

    public boolean isParentaddress2() {
        return parentaddress2;
    }

    public boolean isParentcountry() {
        return parentcountry;
    }

    public boolean isParentcity() {
        return parentcity;
    }

    public boolean isParentstate() {
        return parentstate;
    }

    public boolean isParentzipcode() {
        return parentzipcode;
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

    public boolean isemergency1FirstName() {
        return emergency1FirstName;
    }

    public boolean isemergency1LastName() {
        return emergency1LastName;
    }

    public boolean isemergency1Relationship() {
        return emergency1Relationship;
    }

    public boolean isemergency1Phone() {
        return emergency1Phone;
    }

    public boolean isemergency1Authorized() {
        return emergency1Authorized;
    }

    public boolean isemergency2FirstName() {
        return emergency2FirstName;
    }

    public boolean isemergency2LastName() {
        return emergency2LastName;
    }

    public boolean isemergency2Relationship() {
        return emergency2Relationship;
    }

    public boolean isemergency2Phone() {
        return emergency2Phone;
    }

    public boolean isemergency2Authorized() {
        return emergency2Authorized;
    }

    public boolean isPaymentsamountPaid() {
        return paymentsamountPaid;
    }

    public boolean isPaymentscredit() {
        return paymentscredit;
    }

    public boolean isPaymentsearlyBird() {
        return paymentsearlyBird;
    }

    public boolean isCounselorscounselorID() {
        return counselorscounselorID;
    }

    public boolean isCounselorscounselorEmail() {
        return counselorscounselorEmail;
    }

    public boolean isCounselorscounselorGroup() {
        return counselorscounselorGroup;
    }

    public boolean isCounselorscounselorStatus() {
        return counselorscounselorStatus;
    }

//    public void getFields() {
//        Field [] attributes = this.getClass().getDeclaredFields();
//        for(int i = 0; i < attributes.length; i++) {
//            System.out.println(attributes[i]);
//            System.out.println(attributes[i].get(this));
//        }
//    }

    public List<String> getAttributes() throws IllegalAccessException {
        ArrayList<String> result = new ArrayList<>();
        for (Field f : this.getClass().getDeclaredFields()) {
            if (f.get(this).toString().equals("true")) {
                result.add(f.getName());
            }
        }
        return result;
    }

    public int getYear() {
        return year;
    }
}
