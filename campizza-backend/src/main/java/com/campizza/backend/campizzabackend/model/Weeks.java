/*
     MODELS ARE THE CLASS OBJECTS WE WILL BE CREATING INSTANCES OF

             admins is our table
             columns are id, first_name, last_name, email, phone number
             primary key is id
*/
package com.campizza.backend.campizzabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "weeks")
public class Weeks {
    @Id // indicates that the value below is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // configures the way Adminwe increment the field
    // @TODO: I still want to do more research on what type is better (auto, identity, sequence, table). Seems like there are pros/cons to consider
    @Column(name = "week_id")
    private Long weekId;

    @Column(name = "startOfWeek")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "endOfWeek")
    @Temporal(TemporalType.DATE)
    private Date endDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "attendance_id")
//    private Attendance attendance;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "week_campers_am",
            joinColumns = @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id"))
    private Set<Camper> campersAM;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "week_campers_pm",
            joinColumns = @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id"))
    private Set<Camper> campersPM;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "early_week_campers_am",
            joinColumns = @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id"))
    private Set<Camper> earlyCampersAM;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "early_week_campers_pm",
            joinColumns = @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id"))
    private Set<Camper> earlyCampersPM;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Attendance> attendances;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "week_id")
    private List<Holiday> holidays = new ArrayList<>();

//    @JsonIgnoreProperties("allRegWeeks")
//    @ManyToOne( cascade = CascadeType.ALL)
//    @JoinColumn(name="regWeekID")
//    private RegisteredWeeks registeredWeeks;

    // Default constructor
    public Weeks() {
    }
    // Constructor
    public Weeks(Long weekId, Date startDate, Date endDate) {

        this.weekId = weekId;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Weeks(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    // String output of Admin contents (for testing purposes)
//    @Override
//    public String toString() {
//        return "Admin: id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone number=" + phoneNumber ;
//    }

    public void addAMCamper(Camper camper){
        this.campersAM.add(camper);
    }

    public void addPMCamper(Camper camper){
        this.campersPM.add(camper);
    }

    public void removeAMCamper(Camper camper){

        this.campersAM.remove(camper);
    }

    public void addHoliday(Holiday holiday){
        this.holidays.add(holiday);
    }

    public void removeHoliday(Holiday holiday){

        this.holidays.remove(holiday);
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

    public void removePMCamper(Camper camper){

        this.campersPM.remove(camper);
    }
    // getters and setters for the attributes

    public Long getWeekId() {
        return weekId;
    }

    public void setWeekId(Long weekId) {
        this.weekId = weekId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

//    public Attendance getAttendance() {
//        return attendance;
//    }
//
//    public void setAttendance(Attendance attendance) {
//        this.attendance = attendance;
//    }

    public Set<Camper> getCampersAM() {
        return campersAM;
    }

    public void setCampersAM(Set<Camper> campersAM) {
        this.campersAM = campersAM;
    }

    public Set<Camper> getCampersPM() {
        return campersPM;
    }

    public void setCampersPM(Set<Camper> campersPM) {
        this.campersPM = campersPM;
    }

    public void addCamperAM(Camper camper) {
        this.campersAM.add(camper);
    }

    public void addCamperPM(Camper camper) {
        this.campersPM.add(camper);
    }

    public void addEarlyCamperAM(Camper camper) {
        this.earlyCampersAM.add(camper);
    }

    public void addEarlyCamperPM(Camper camper) {
        this.earlyCampersPM.add(camper);
    }

    public Set<Camper> getEarlyCampersAM() {
        return earlyCampersAM;
    }

    public void setEarlyCampersAM(Set<Camper> earlyCampersAM) {
        this.earlyCampersAM = earlyCampersAM;
    }

    public Set<Camper> getEarlyCampersPM() {
        return earlyCampersPM;
    }

    public void setEarlyCampersPM(Set<Camper> earlyCampersPM) {
        this.earlyCampersPM = earlyCampersPM;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void addAttendance(Attendance a) {
        this.attendances.add(a);
    }


    //    public RegisteredWeeks getRegisteredWeeks() {
//        return registeredWeeks;
//    }
//
//    public void setRegisteredWeeks(RegisteredWeeks registeredWeeks) {
//        this.registeredWeeks = registeredWeeks;
//    }
//    public List<Camper> getCampers() {
//        return campers;
//    }
//
//    public void setCampers(List<Camper> campers) {
//        this.campers = campers;
//    }

    //    public List<Camper> getCampers() {
//        return campers;
//    }
//
//    public void setCampers(List<Camper> campers) {
//        this.campers = campers;
//    }


}
