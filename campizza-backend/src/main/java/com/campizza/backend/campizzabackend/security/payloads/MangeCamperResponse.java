package com.campizza.backend.campizzabackend.security.payloads;

import com.campizza.backend.campizzabackend.model.Camper;
import com.campizza.backend.campizzabackend.model.HouseHold;
import com.campizza.backend.campizzabackend.model.Weeks;
import com.campizza.backend.campizzabackend.model.camperWeeks;

import java.util.List;
import java.util.Optional;

public class MangeCamperResponse {
    Long id;
    String firstName;
    String lastName;
    String guardianname1;
    String guardianemail1;
    String guardianphone1;
    String guardianname2;
    String guardianemail2;
    String guardianphone2;
    List<camperWeeks> weeks;
    String group;
    Float amountpaid;
    Float credit;

    public MangeCamperResponse() {
    }

    public MangeCamperResponse(Camper camper, HouseHold parent, List<camperWeeks> weeksRegistered) {
        this.id = camper.getId();
        this.firstName = camper.getFirstName();
        this.lastName = camper.getLastName();
        this.guardianname1 = parent.getGuardianNameFirst1() + " " + parent.getGuardianNameLast1();
        this.guardianemail1 = parent.getGuardianEmail1();
        this.guardianphone1 = parent.getGuardian1Phone1();
        this.guardianname2 = parent.getGuardianNameFirst2() + " " + parent.getGuardianNameLast2();
        this.guardianemail2 = parent.getGuardianEmail2();
        this.guardianphone2  = parent.getGuardian2Phone1();
        this.group = camper.getGroup();
        this.weeks = weeksRegistered;
        this.amountpaid = camper.getPaid();
        this.credit = camper.getCredit();


    }

    public MangeCamperResponse(Camper camper, String parent, List<camperWeeks> weeksRegistered) {
        System.out.println(camper.getId());
        this.id = camper.getId();
        this.firstName = camper.getFirstName();
        this.lastName = camper.getLastName();
        this.guardianname1 = "Unavailable";
        this.guardianemail1 = "Unavailable";
        this.guardianphone1 = "Unavailable";
        this.guardianname2 = "Unavailable";
        this.guardianemail2 = "Unavailable";
        this.guardianphone2  = "Unavailable";
        this.group = camper.getGroup();
        this.weeks = weeksRegistered;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getGuardianname1() {
        return guardianname1;
    }

    public void setGuardianname1(String guardianname1) {
        this.guardianname1 = guardianname1;
    }

    public String getGuardianemail1() {
        return guardianemail1;
    }

    public void setGuardianemail1(String guardianemail1) {
        this.guardianemail1 = guardianemail1;
    }

    public String getGuardianphone1() {
        return guardianphone1;
    }

    public void setGuardianphone1(String guardianphone1) {
        this.guardianphone1 = guardianphone1;
    }

    public String getGuardianname2() {
        return guardianname2;
    }

    public void setGuardianname2(String guardianname2) {
        this.guardianname2 = guardianname2;
    }

    public String getGuardianemail2() {
        return guardianemail2;
    }

    public void setGuardianemail2(String guardianemail2) {
        this.guardianemail2 = guardianemail2;
    }

    public String getGuardianphone2() {
        return guardianphone2;
    }

    public void setGuardianphone2(String guardianphone2) {
        this.guardianphone2 = guardianphone2;
    }

    public List<camperWeeks> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<camperWeeks> weeks) {
        this.weeks = weeks;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Float getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(Float amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }
}
